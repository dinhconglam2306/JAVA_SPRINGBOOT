$(document).ready(function() {
    $('#contactForm').on('submit', function(e) {
        e.preventDefault();
        
        // Reset error messages
        $('.error-message').remove();
        $('#successMessage').addClass('d-none');

        $.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            data: $(this).serialize(),
            success: function(response) {
                if (response.status === 'success') {
                    // Hiển thị thông báo thành công
                    $('#successMessage')
                        .removeClass('d-none alert-danger')
                        .addClass('alert-success')
                        .text(response.message);
                    
                    // Reset form
                    $('#contactForm')[0].reset();
                } else {
                    // Hiển thị lỗi validation
                    Object.keys(response.errors).forEach(function(field) {
                        const errorDiv = $('<div>')
                            .addClass('text-danger error-message')
                            .text(response.errors[field]);
                        $(`#${field}`).after(errorDiv);
                    });

                    $('#successMessage')
                        .removeClass('d-none alert-success')
                        .addClass('alert-danger')
                        .text(response.message);
                }
            },
            error: function() {
                $('#successMessage')
                    .removeClass('d-none alert-success')
                    .addClass('alert-danger')
                    .text('Có lỗi xảy ra, vui lòng thử lại sau!');
            }
        });
    });

    // Load More functionality
    $('.load-more-btn').on('click', function() {
        const button = $(this);
        const categoryId = button.data('category-id');
        const offset = button.data('offset');
        // Tìm container chứa danh sách bài viết
        const categoryContainer = button.closest('.row').prev().find('.m-t--40.p-b-40');

        // Disable button while loading
        button.prop('disabled', true).text('Đang tải...');

        $.ajax({
            url: '/load-more',
            type: 'GET',
            data: {
                categoryId: categoryId,
                offset: offset
            },
            success: function(response) {
                if (response.articles && response.articles.length > 0) {
                    // Append new articles based on layout
                    if (response.layout === 'LIST') {
                        response.articles.forEach(function(article) {
                            const articleHtml = `
                                <div class="flex-wr-sb-s p-t-40 p-b-15 how-bor2">
                                    <a href="/${article.slug}-a${article.id}.html"
                                       class="size-w-8 wrap-pic-w hov1 trans-03 w-full-sr575 m-b-25">
                                        <img src="/uploads/images/${article.image || 'no-image.jpg'}" alt="${article.name}">
                                    </a>
                                    <div class="size-w-9 w-full-sr575 m-b-25">
                                        <h5 class="p-b-12">
                                            <a href="/${article.slug}-a${article.id}.html"
                                               class="f1-l-1 cl2 hov-cl10 trans-03 respon2">
                                                ${article.name}
                                            </a>
                                        </h5>
                                        <div class="cl8 p-b-18">
                                            <a href="${article.author.url}" class="f1-s-4 cl8 hov-cl10 trans-03">
                                                ${article.author.name}
                                            </a>
                                            <span class="f1-s-3 m-rl-3">-</span>
                                            <span class="f1-s-3">
                                                ${new Date(article.publishDate).toLocaleDateString('vi-VN')}
                                            </span>
                                        </div>
                                        <p>
                                            ${article.content ? (article.content.length > 100 ? article.content.substring(0, 100) + '...' : article.content) : ''}
                                        </p>
                                        <a href="/${article.slug}-a${article.id}.html" 
                                           class="f1-s-1 cl9 hov-cl10 trans-03">
                                            Xem Thêm <i class="m-l-2 fa fa-long-arrow-alt-right"></i>
                                        </a>
                                    </div>
                                </div>
                            `;
                            categoryContainer.append(articleHtml);
                        });
                    } else if (response.layout === 'GRID') {
                        const gridContainer = button.closest('.row').prev().find('.row');
                        response.articles.forEach(function(article) {
                            const articleHtml = `
                                <div class="col-sm-6 p-r-25 p-r-15-sr991">
                                    <div class="m-b-45 d-flex flex-column h-100">
                                        <a href="/${article.slug}-a${article.id}.html"
                                           class="wrap-pic-w hov1 trans-03">
                                            <div class="image-container" style="height: 200px; overflow: hidden;">
                                                <img src="/uploads/images/${article.image || 'no-image.jpg'}" alt="${article.name}" style="width: 100%; height: 100%; object-fit: cover;">
                                            </div>
                                        </a>
                                        <div class="p-t-16 flex-grow-1">
                                            <h5 class="p-b-5">
                                                <a href="/${article.slug}-a${article.id}.html"
                                                   class="f1-m-3 cl2 hov-cl10 trans-03">
                                                    ${article.name}
                                                </a>
                                            </h5>
                                            <span class="cl8">
                                                <a href="${article.author.url}" class="f1-s-4 cl8 hov-cl10 trans-03">
                                                    ${article.author.name}
                                                </a>
                                                <span class="f1-s-3 m-rl-3">-</span>
                                                <span class="f1-s-3">
                                                    ${new Date(article.publishDate).toLocaleDateString('vi-VN')}
                                                </span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            `;
                            gridContainer.append(articleHtml);
                        });
                    }

                    // Update offset for next load
                    button.data('offset', offset + response.articles.length);
                    
                    // Hiển thị hoặc ẩn nút dựa vào việc còn bài viết hay không
                    if (response.hasMore) {
                        button.prop('disabled', false).text('Xem thêm ...');
                    } else {
                        button.hide();
                    }
                } else {
                    // Không có bài viết nào: ẩn nút luôn
                    button.hide();
                }
            },
            error: function(xhr) {
                console.error('Error loading more articles:', xhr.responseText);
                button.prop('disabled', false).text('Xem thêm ...');
                alert('Có lỗi xảy ra khi tải thêm bài viết. Vui lòng thử lại sau!');
            }
        });
    });
});

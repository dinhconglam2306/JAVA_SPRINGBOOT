$(document).ready(function() {
    const $actionSelect     = $('[name="action-multi"]');
    const $applyButton      = $('#apply-button');
    const $form             = $('#batchUpdateForm');
    const $itemCheckboxes   = $('.itemCheckbox');
    const $checkAll         = $('#checkAll');
    const $changeSize       = $('#change-size');

    $applyButton.on('click', function(event) {
        event.preventDefault();

        Swal.fire({
            title: 'Bạn có chắc chắn?',
            text: "Các mục mà bạn đã chọn sẽ thay đổi!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Có, thực hiện!',
            cancelButtonText: 'Hủy'
        }).then((result) => {
            if (result.isConfirmed) {
                $form.submit();
            }
        });
    });

    $checkAll.on('change', function() {
        const isChecked = this.checked;
        $itemCheckboxes.each(function(index, checkbox) {
            const $orderingInput = $('.ordering-input').eq(index);
            $(checkbox).prop('checked', isChecked);
            $orderingInput.prop('disabled', !isChecked);
        });
        updateCheckboxState();
    });

    $actionSelect.on('change', function() {
        const selectedOption = $(this).val();
        $form.attr('action', selectedOption);
        updateCheckboxState();
    });

    $itemCheckboxes.each(function(index, checkbox) {
        const $orderingInput = $('.ordering-input').eq(index);
        $orderingInput.prop('disabled', !$(checkbox).prop('checked'));

        $(checkbox).on('change', function() {
            $orderingInput.prop('disabled', !$(this).prop('checked'));

            if (!$(this).prop('checked')) {
                $checkAll.prop('checked', false);
            } else if ($itemCheckboxes.filter(':checked').length === $itemCheckboxes.length) {
                $checkAll.prop('checked', true);
            }

            updateCheckboxState();
        });
    });

    $changeSize.on('change', function() {
        window.location.href = $(this).val();
    });    

    function updateCheckboxState() {
        const anyChecked = $itemCheckboxes.filter(':checked').length > 0;
        const validActionSelected = $actionSelect.val() !== "";
        $applyButton.prop('disabled', !(anyChecked && validActionSelected));
    }
});
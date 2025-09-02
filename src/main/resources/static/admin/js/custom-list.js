$(document).ready(function () {
  const $actionSelect       = $('[name="action-multi"]');
  const $applyButton        = $("#apply-button");
  const $form               = $("#batchUpdateForm");
  const $itemCheckboxes     = $(".itemCheckbox");
  const $deleteButton       = $(".delete-button");
  const $checkAll           = $("#checkAll");
  const $changeSize         = $("#change-size");
  const $changeSelect       = $(".status-select");
  const $nameInput          = $('#inputName');
  const $slugInput          = $('#inputSlug');


  $nameInput.on("input", function () {
        const slug = generateSlug($nameInput.val());
        $slugInput.val(slug);
  });

  $changeSelect.on("change", function () {
    const id = $(this).data("item-id");
    const status = $(this).val();
    $.ajax({
      url: window.location.pathname + "/change-status",
      method: "POST",
      data: { id: id, status: status },
      success: function (response) {
        showToast(response.status, response.message, "#d4edda", "#155724");
      },
      error: function (xhr) {
        showToast(
          xhr.responseJSON.status,
          xhr.responseJSON.message,
          "#f8d7da",
          "#721c24"
        );
      },
    });
  });

  $deleteButton.on("click", function (event) {
    event.preventDefault();
    const url = $(this).attr("href");

    Swal.fire({
      title: "Xác nhận xóa?",
      text: "Bạn có chắc muốn xóa mục này?",  
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Có, hãy xóa!",
      cancelButtonText: "Hủy",
    }).then((result) => {
      if (result.isConfirmed) {
        window.location.href = url;
      }
    });
  });

  $applyButton.on("click", function (event) {
    event.preventDefault();

    Swal.fire({
      title: "Bạn có chắc chắn?",
      text: "Các mục mà bạn đã chọn sẽ thay đổi!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Có, thực hiện!",
      cancelButtonText: "Hủy",
    }).then((result) => {
      if (result.isConfirmed) {
        $form.submit();
      }
    });
  });

  $checkAll.on("change", function () {
    const isChecked = this.checked;
    $itemCheckboxes.each(function (index, checkbox) {
      const $orderingInput = $(".ordering-input").eq(index);
      $(checkbox).prop("checked", isChecked);
      $orderingInput.prop("disabled", !isChecked);
    });
    updateCheckboxState();
  });

  $actionSelect.on("change", function () {
    const selectedOption = $(this).val();
    $form.attr("action", selectedOption);
    updateCheckboxState();
  });

  $itemCheckboxes.each(function (index, checkbox) {
    const $orderingInput = $(".ordering-input").eq(index);
    $orderingInput.prop("disabled", !$(checkbox).prop("checked"));

    $(checkbox).on("change", function () {
      $orderingInput.prop("disabled", !$(this).prop("checked"));

      if (!$(this).prop("checked")) {
        $checkAll.prop("checked", false);
      } else if (
        $itemCheckboxes.filter(":checked").length === $itemCheckboxes.length
      ) {
        $checkAll.prop("checked", true);
      }

      updateCheckboxState();
    });
  });

  $changeSize.on("change", function () {
    window.location.href = $(this).val();
  });

  function updateCheckboxState() {
    const anyChecked = $itemCheckboxes.filter(":checked").length > 0;
    const validActionSelected = $actionSelect.val() !== "";
    $applyButton.prop("disabled", !(anyChecked && validActionSelected));
  }

  function showToast(icon, title, background, color) {
    Swal.fire({
      toast: true,
      position: "top-end",
      icon: icon,
      title: title,
      showConfirmButton: false,
      timer: 3000,
      timerProgressBar: true,
      background: background,
      color: color,
    });
  }

  function generateSlug(value) {
        return value
            .toLowerCase()
            .trim()
            .replace(/á|à|ả|ã|ạ|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ/g, "a")
            .replace(/é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ/g, "e")
            .replace(/i|í|ì|ỉ|ĩ|ị/g, "i")
            .replace(/ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ/g, "o")
            .replace(/ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự/g, "u")
            .replace(/ý|ỳ|ỷ|ỹ|ỵ/g, "y")
            .replace(/đ/g, "d")
            .replace(/[^a-z0-9 -]/g, "")
            .replace(/\s+/g, "-")
            .replace(/-+/g, "-");
    }
});

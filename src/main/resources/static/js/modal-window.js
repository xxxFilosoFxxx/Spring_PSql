// function showModal() {
//     var modal = document.querySelector('.edit-income');
//     var button = document.querySelector('.show-edit-income');
//
//     button.onclick = function () {
//         modal.style.display = "show";
//     };
// }

// JQuery
$(function () {
    $('.show-edit-income').click(function () {
        $('.edit-income').modal('show');
    });

    $('.show-edit-dues').click(function () {
        $('.edit-dues').modal('show');
    });

    $('.show-edit-institusion').click(function () {
        $('.edit-institusion').modal('show');
    });
});
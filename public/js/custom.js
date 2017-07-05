(function($) {
    "use strict";

    // Options for Message
    //----------------------------------------------
    var options = {
        'btn-loading': '<i class="fa fa-spinner fa-pulse"></i>',
        'btn-success': '<i class="fa fa-check"></i>',
        'btn-error': '<i class="fa fa-remove"></i>',
        'msg-success': 'All Good! Redirecting...',
        'msg-error': 'Wrong login credentials!',
        'useAJAX': true,
    };

    // Login Form
    //----------------------------------------------
    // Validation
    $("#login-form").validate({
        rules: {
            email: "required",
            password: "required",
        },
        errorClass: "form-invalid"
    });

    // Form Submission
    $("#login-form").submit(function() {
        remove_loading($(this));

        if(options['useAJAX'] == true)
        {
            // Dummy AJAX request (Replace this with your AJAX code)
            // If you don't want to use AJAX, remove this
            dummy_submit_form($(this));

            // Cancel the normal submission.
            // If you don't want to use AJAX, remove this
            return false;
        }
    });

    // Register Form
    //----------------------------------------------
    // Validation
    $("#register-form").validate({
        rules: {
            userName: "required",
            password: {
                required: true,
                minlength: 5
            },
            reg_password_confirm: {
                required: true,
                minlength: 5,
                equalTo: "#register-form [name=password]"
            },
            email: {
                required: true,
                email: true
            },
            fullName: "required",
            reg_agree: "required",
        },
        errorClass: "form-invalid",
        errorPlacement: function( label, element ) {
            if( element.attr( "type" ) === "checkbox" || element.attr( "type" ) === "radio" ) {
                element.parent().append( label ); // this would append the label after all your checkboxes/labels (so the error-label will be the last element in <div class="controls"> )
            }
            else {
                label.insertAfter( element ); // standard behaviour
            }
        }
    });

    // Form Submission
    $("#register-form").submit(function() {
        remove_loading($(this));

        if(options['useAJAX'] == true)
        {
            // Dummy AJAX request (Replace this with your AJAX code)
            // If you don't want to use AJAX, remove this
            dummy_submit_form($(this));

            // Cancel the normal submission.
            // If you don't want to use AJAX, remove this
            return false;
        }
    });

    // Forgot Password Form
    //----------------------------------------------
    // Validation
    $("#forgot-password-form").validate({
        rules: {
            fp_email: "required",
        },
        errorClass: "form-invalid"
    });

    // Form Submission
    $("#forgot-password-form").submit(function() {
        remove_loading($(this));

        if(options['useAJAX'] == true)
        {
            // Dummy AJAX request (Replace this with your AJAX code)
            // If you don't want to use AJAX, remove this
            dummy_submit_form($(this));

            // Cancel the normal submission.
            // If you don't want to use AJAX, remove this
            return false;
        }
    });

    // Loading
    //----------------------------------------------
    // function remove_loading($form)
    // {
    //     $form.find('[type=submit]').removeClass('error success');
    //     $form.find('.login-form-main-message').removeClass('show error success').html('');
    // }
    //
    // function form_loading($form)
    // {
    //     $form.find('[type=submit]').addClass('clicked').html(options['btn-loading']);
    // }
    //
    // function form_success($form)
    // {
    //     $form.find('[type=submit]').addClass('success').html(options['btn-success']);
    //     $form.find('.login-form-main-message').addClass('show success').html(options['msg-success']);
    // }
    //
    // function form_failed($form)
    // {
    //     $form.find('[type=submit]').addClass('error').html(options['btn-error']);
    //     $form.find('.login-form-main-message').addClass('show error').html(options['msg-error']);
    // }
    //For create card modal in editdeck
// (function ($) {
//     $(function () {
//
//         var addFormGroup = function (event) {
//             event.preventDefault();
//
//             var $formGroup = $(this).closest('.form-group');
//             var $multipleFormGroup = $formGroup.closest('.multiple-form-group');
//             var $formGroupClone = $formGroup.clone();
//
//             $(this)
//                 .toggleClass('btn-success btn-add btn-danger btn-remove')
//                 .html('–');
//
//             $formGroupClone.find('input').val('');
//             $formGroupClone.find('.concept').text('Phone');
//             $formGroupClone.insertAfter($formGroup);
//
//             var $lastFormGroupLast = $multipleFormGroup.find('.form-group:last');
//             if ($multipleFormGroup.data('max') <= countFormGroup($multipleFormGroup)) {
//                 $lastFormGroupLast.find('.btn-add').attr('disabled', true);
//             }
//         };
//
//         var removeFormGroup = function (event) {
//             event.preventDefault();
//
//             var $formGroup = $(this).closest('.form-group');
//             var $multipleFormGroup = $formGroup.closest('.multiple-form-group');
//
//             var $lastFormGroupLast = $multipleFormGroup.find('.form-group:last');
//             if ($multipleFormGroup.data('max') >= countFormGroup($multipleFormGroup)) {
//                 $lastFormGroupLast.find('.btn-add').attr('disabled', false);
//             }
//
//             $formGroup.remove();
//         };
//
//
//
//         var countFormGroup = function ($form) {
//             return $form.find('.form-group').length;
//         };
//
//         $(document).on('click', '.btn-add', addFormGroup);
//         $(document).on('click', '.btn-remove', removeFormGroup);
//         $(document).on('click', '.dropdown-menu a', selectFormGroup);
//
//     });
// })(jQuery);

    // Dummy Submit Form (Remove this)
    //----------------------------------------------
    // This is just a dummy form submission. You should use your AJAX function or remove this function if you are not using AJAX.
    $(document).ready(function() {
        $('#example').DataTable();
    } );


})(jQuery);
//for the edit database
// $(document).ready(function(){
//     $("#mytable #checkall").click(function () {
//         if ($("#mytable #checkall").is(':checked')) {
//             $("#mytable input[type=checkbox]").each(function () {
//                 $(this).prop("checked", true);
//             });
//
//         } else {
//             $("#mytable input[type=checkbox]").each(function () {
//                 $(this).prop("checked", false);
//             });
//         }
//     });
//
//     $("[data-toggle=tooltip]").tooltip();
// });
// //For create card modal in editdeck
// (function ($) {
//     $(function () {
//
//         var addFormGroup = function (event) {
//             event.preventDefault();
//
//             var $formGroup = $(this).closest('.form-group');
//             var $multipleFormGroup = $formGroup.closest('.multiple-form-group');
//             var $formGroupClone = $formGroup.clone();
//
//             $(this)
//                 .toggleClass('btn-success btn-add btn-danger btn-remove')
//                 .html('–');
//
//             $formGroupClone.find('input').val('');
//             $formGroupClone.find('.concept').text('Phone');
//             $formGroupClone.insertAfter($formGroup);
//
//             var $lastFormGroupLast = $multipleFormGroup.find('.form-group:last');
//             if ($multipleFormGroup.data('max') <= countFormGroup($multipleFormGroup)) {
//                 $lastFormGroupLast.find('.btn-add').attr('disabled', true);
//             }
//         };
//
//         var removeFormGroup = function (event) {
//             event.preventDefault();
//
//             var $formGroup = $(this).closest('.form-group');
//             var $multipleFormGroup = $formGroup.closest('.multiple-form-group');
//
//             var $lastFormGroupLast = $multipleFormGroup.find('.form-group:last');
//             if ($multipleFormGroup.data('max') >= countFormGroup($multipleFormGroup)) {
//                 $lastFormGroupLast.find('.btn-add').attr('disabled', false);
//             }
//
//             $formGroup.remove();
//         };
//
//
//
//         var countFormGroup = function ($form) {
//             return $form.find('.form-group').length;
//         };
//
//         $(document).on('click', '.btn-add', addFormGroup);
//         $(document).on('click', '.btn-remove', removeFormGroup);
//         $(document).on('click', '.dropdown-menu a', selectFormGroup);
//
//     });
// })(jQuery);

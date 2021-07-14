// jquery validation
jQuery.validator.setDefaults({
	errorPlacement: function (error, element) {
		if ($(element).hasClass("select2")) {
			// select 2
			error.addClass("help-block invalid-feedback");
			element.parent("div").addClass("has-error");
			error.insertAfter(element.parent("div").children()[1]);
			var select2 = $(element).next("span");
			// $(select2).find(".selection").children("span").removeClass('select2-success').addClass('select2-error');
		} else if ($(element).hasClass("dropify")) {
			error.addClass("help-block invalid-feedback dropify-error");
			element.parent("div").addClass("has-error");
			element.parent("div").find(".dropify-error").empty()
			error.insertAfter(element.parent("div").find(".dropify-error"));
		} else {
			// Add the `help-block` class to the error element
			error.addClass("help-block invalid-feedback");
			// Add `has-error` class to the parent div.form-group
			// in order to add icons to inputs
			element.parent("div").addClass("has-error");
			if (element.prop("type") === "checkbox") {
				error.insertAfter(element.parent("label"));
			} else {
				error.insertAfter(element);
			}
		}
	},
	highlight: function (element, errorClass, validClass) {
		if ($(element).hasClass("select2")) {
			$(element).parent("div").parent("div").addClass("has-error").removeClass("has-success");
			$(element).parent("div").addClass("is-invalid").removeClass("is-valid");
		} else {
			$(element).parent("div").addClass("has-error").removeClass("has-success");
			$(element).addClass("is-invalid").removeClass("is-valid");
		}
	},
	unhighlight: function (element, errorClass, validClass) {
		if ($(element).hasClass("select2")) {
			$(element).parent("div").parent("div").addClass("has-success").removeClass("has-error");
			$(element).parent("div").addClass("is-valid").removeClass("is-invalid");
		} else {
			$(element).parent("div").addClass("has-success").removeClass("has-error");
			$(element).addClass("is-valid").removeClass("is-invalid");
			$(element).next("span").remove()
			$(element).next("label").remove()
		}
	}
});

//validate ajax
validate = (data, url, form = "form") => {
	$.ajax({
		data: JSON.stringify(data),
		url: url,
		type: "POST",
		contentType: "application/json",
		dataType: 'json',
		success: function (e) {
			swal.close();
			$(form).addClass("success");
			$(form).submit();
		},
		beforeSend: function () {
			showLoading();
		},
		error: function (e) {
			console.log(e)
			if (e.status == 400 && e.responseJSON.code == "400") {
				e.responseJSON.message.map(function (ex) {
					swal.close();
					iziToast.warning({
						title: 'Info',
						message: ex,
						position: 'topRight'
					});
				})
			} else {
				swal.close();
				iziToast.error({
					title: 'Warning',
					message: "Bad Request",
					position: 'topRight'
				});
			}
		}
	});
}

function showLoading() {
	swal.fire({
		title: 'Now loading',
		allowEscapeKey: false,
		allowOutsideClick: false,
		onOpen: () => {
			swal.showLoading();
		}
	});
}

$.validator.addMethod("alpha", function (value, element) {
	return this.optional(element) || /^[a-zA-Z]+$/.test(value)
}, "Only characters allowed.");

$.validator.addMethod("alphaWithSpace", function (value, element) {
	return this.optional(element) || /^[a-zA-Z ]+$/.test(value)
}, "Only characters and space allowed.");

$.validator.addMethod("alphaNumeric", function (value, element) {
	return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value)
}, "Only Characters and number allowed.");

$.validator.addMethod("numeric", function (value, element) {
	return this.optional(element) || /^[0-9]+$/.test(value)
}, "Only number allowed.");

function deleteData(url) {
	Swal.fire({
		title: "Are you sure delete data ?",
		showCancelButton: true,
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.replace(url);
		}
	})
}

function thousandSeparators(x) {
	return "Rp. " + x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}
function addToCart(qty, idproduct) {
	var data = {
		id: idproduct,
		qty: qty,
	}
	return new Promise(function (myResolve, myReject) {
		$.ajax({
			url: 'frontend/shop/addToCart',
			type: "POST",
			data: data,
			dataType: 'json',
			success: function (e) {
				if (!e.success) {
					e.message.map(function (ex) {
						iziToast.warning({
							title: 'Info',
							message: ex,
							position: 'topRight'
						});
					})
					myReject();
				} else {
					$("#count-cart").html("[" + e.cart + "]")
					swal.close();
					iziToast.success({
						title: 'BERHASIL',
						message: "DIMASUKAN KE KERANJANG",
						position: 'topRight'
					});
					myResolve()
				}
			},
			beforeSend: function () {
				showLoading();
			},
			error: function (e) {
				swal.close();
				iziToast.error({
					title: 'Warning',
					message: "Failed for add cart",
					position: 'topRight'
				});
				myReject();
			}
		})
	})
}

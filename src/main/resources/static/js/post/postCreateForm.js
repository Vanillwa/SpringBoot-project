const handleSubmit = (e, f) => {
	e.preventDefault();
	
	let body = {
		title: f.title.value,
		content: f.content.value,
		user_id : $('#user_id').val()
	}

	fetch('/api/post', { method: "post", body: JSON.stringify(body), headers: { 'Content-Type': 'application/json' } })
		.then((res) => res.text())
		.then((text) => {
			if (text == '') {
				alert('오류 발생');
				return;
			}

			location.href = '/posts'
		})
}
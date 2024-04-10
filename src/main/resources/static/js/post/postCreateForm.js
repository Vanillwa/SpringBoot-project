const handleSubmit = (e, f) => {
	e.preventDefault();
	
	let body = {
		title: f.title.value,
		content: f.content.value
	}

	fetch('/api/post', { method: "post", body: JSON.stringify(body), headers: { 'Content-Type': 'application/json' } })
		.then((res) => res.text())
		.then((text) => {
			if (text == '') {
				alert('오류 발생');
				return;
			}
			location.href = `/post/list/${text}`
		})
}

const handleCancle = ()=>{
	if(!confirm('작성한 모든 내용이 사라집니다'))
		return;
	history.back();
}
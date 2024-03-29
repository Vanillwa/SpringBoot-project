const handleDelete = () => {	
	if(!confirm('정말 삭제 하시겠습니까?'))
		return;
	
	fetch(`/api/post/${postId}`, { method: 'delete' })
		.then(res => res.text())
		.then(text => {
			if(text === 'success'){
				alert('삭제되었습니다.')
				location.href="/posts"
			}else if(text ==='NoExist'){
				alert('글이 존재하지 않습니다')
			}else if(text ==='NoAuth'){
				alert('권한이 없습니다')
			}
		})
}

const handleUpdateForm = ()=>{
	location.href=`/posts/${postId}/update`
}
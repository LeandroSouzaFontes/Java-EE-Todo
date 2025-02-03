

function validar(){
	let name = frmRegister.name.value
	let email = frmRegister.email.value
	let senha = frmRegister.senha.value
	let confirmarSenha = frmRegister.confirmarSenha.value
	
	if(name === ""){
		alert("Preenchar o campo nome")
		frmRegister.name.focus()
		return false
	}else if(email === ""){
		alert("Preencha o campo email")
		frmRegister.email.focus()
		return false
	}
	else if(senha.length<6){
			alert("Insira uma senha com mais de 6 caracteres")
			frmRegister.senha.focus()
			return false
		}
		else if(confirmarSenha !==senha){
				alert("As duas senhas tem que ser igual")
				frmRegister.confirmarSenha.focus()
				return false
			}else{
				document.forms["frmRegister"].submit()
			}
}


function validarEditTodo(){
	let taskName = frmEditTodo.taskName.value
	
	if(taskName === ""){
		alert("Preencher o campo da task")
		frmEditTodo.taskName.focus()
		return false
	}else{
		document.forms["frmEditTodo"].submit()

}
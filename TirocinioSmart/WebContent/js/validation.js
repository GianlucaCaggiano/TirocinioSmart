$('#ajax-register-form').bootstrapValidator({
	//        live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		matricola : {
			validators : {
				notEmpty : {
					message : 'Inserisci matricola'
				},
				regexp : {
					regexp : '^[0-9]',
					message : 'Matricola non valida'
				},
				stringLength: {
                    enabled: true,
                    min: 10,
                    max: 10,
                    message: 'La matricola deve essere di 10 caratteri.'
				}
			}
		},
		nome : {
			validators : {
				notEmpty : {
					message : 'Inserisci nome'
				}
			}
		},
		cognome : {
			validators : {
				notEmpty : {
					message : 'Inserisci cognome'
				}
			}
		},
		email : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email'
				},
				regexp : {
					regexp : '^[a-zA-Z0-9.]+\@studenti\.unisa\.it',
					message : 'Email non valida'
				},
				/*Check email*/
			},
			message:" "
		},
		password : {
			validators : {
				notEmpty : {
					message : 'Inserisci la password'
				},
                stringLength: {
                    enabled: true,
                    min: 6,
                    max: 32,
                    message: 'La password deve essere tra 6 e 32 caratteri.'
                }
			}
		},
		confirmpassword : {
			validators : {
				notEmpty : {
					message : 'Inserisci conferma password'
				},
				identical : {
					field : 'password',
					message : 'La password è diversa'
				}
			}
		}
	}
});

$('#ajax-login-form').bootstrapValidator({
	//        live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		emailLogin : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email'
				},
				regexp : {
					regexp : '^[a-zA-Z0-9.]+\@studenti\.unisa\.it',
					message : 'Email non valida'
				}
			}
		},
		passwordLogin : {
			validators : {
				notEmpty : {
					message : 'Inserisci la password'
				}
			}
		}
	}
});

$('#resetPassword').bootstrapValidator({
	//        live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		resetemail : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email.'
				},
				regexp : {
					regexp : '^[a-zA-Z0-9.]+\@studenti\.unisa\.it',
					message : 'Email non valida'
				},
				remote:{
					url:"CheckEmailReset",
					type:"POST",
					message:"Account non presente."
				}
			},
			message:" "
		}
	}
});


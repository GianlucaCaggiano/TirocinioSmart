$('#ajax-registerStudente-form').bootstrapValidator({
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
				},
				regexp : {
					regexp : /^[A-Z a-z]+$/,
					message : 'Nome non valido'
				},
			}
		},
		cognome : {
			validators : {
				notEmpty : {
					message : 'Inserisci cognome'
				},
				regexp : {
					regexp : /^[A-Z a-z]+$/,
					message : 'Cognome non valido'
				},
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
                },
                regexp : {
					regexp : /^[0-9a-zA-Z._-]+$/,
					message : 'Formato password non valido'
				},
			}
		},
		confirmpassword : {
			validators : {
				notEmpty : {
					message : 'Inserisci conferma password'
				},
				identical : {
					field : 'password',
					message : 'La password &egrave diversa'
				}
			}
		},
		dataNascita : {
			validators : {
				notEmpty : {
					message : 'Inserisci la data di nascita'
				},
				date: {
                    message: 'La data non &egrave valida',
                    format: 'YYYY/MM/DD'
                }
			}
		},
		luogoNascita : {
			validators : {
				notEmpty : {
					message : 'Inserisci il luogo di nascita'
				},
				 regexp : {
						regexp : /^[A-Z a-z]+$/,
						message : 'Formato non valido'
				}
			}
		}
	}
});

$('#ajax-registerAzienda-form').bootstrapValidator({
	//        live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		nome : {
			validators : {
				notEmpty : {
					message : 'Inserisci nome'
				},
				regexp : {
					regexp : /^[A-Z a-z]+$/,
					message : 'Nome non valido'
				},
			}
		},
		cognome : {
			validators : {
				notEmpty : {
					message : 'Inserisci cognome'
				},
				regexp : {
					regexp : /^[A-Z a-z]+$/,
					message : 'Cognome non valido'
				},
			}
		},
		email : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email'
				},
				regexp : {
					regexp : /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
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
                },
                regexp : {
					regexp : /^[0-9a-zA-Z._-]+$/,
					message : 'Formato password non valido'
				},
			}
		},
		confirmpassword : {
			validators : {
				notEmpty : {
					message : 'Inserisci conferma password'
				},
				identical : {
					field : 'password',
					message : 'La password &egrave diversa'
				}
			}
		},
		dataNascita : {
			validators : {
				notEmpty : {
					message : 'Inserisci la data di nascita'
				},
				date: {
                    message: 'La data non &egrave valida',
                    format: 'YYYY/MM/DD'
                }
			}
		},
		luogoNascita : {
			validators : {
				notEmpty : {
					message : 'Inserisci il luogo di nascita'
				},
				 regexp : {
						regexp : /^[A-Z a-z]+$/,
						message : 'Formato non valido'
				}
			}
		},
		citta : {
			validators : {
				notEmpty : {
					message : 'Inserisci la citt&agrave'
				},
				 regexp : {
						regexp : /^[A-Z a-z]+$/,
						message : 'Formato non valido'
				}
			}
		},
		cap : {
			validators : {
				notEmpty : {
					message : 'Inserisci il CAP'
				},
				 regexp : {
						regexp : /^[0-9]+$/,
						message : 'Formato non valido'
				},
				stringLength: {
                    enabled: true,
                    min: 5,
                    max: 5,
                    message: 'Il CAP &egrave composto da 5 numeri'
                }
			}
		},
		via : {
			validators : {
				notEmpty : {
					message : 'Inserisci la via'
				},
				 regexp : {
						regexp : /^[0-9a-zA-Z._-\s]+$/,
						message : 'Formato non valido'
				}
			}
		},
		denominazione : {
			validators : {
				notEmpty : {
					message : 'Inserisci la ragione sociale'
				},
				 regexp : {
						regexp : /^[0-9a-zA-Z._-\s]+$/,
						message : 'Formato non valido'
				}
			}
		},
	}
});

$('#ajax-registerProfessore-form').bootstrapValidator({
	//        live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		nome : {
			validators : {
				notEmpty : {
					message : 'Inserisci nome'
				},
				regexp : {
					regexp : /^[A-Z a-z]+$/,
					message : 'Nome non valido'
				},
			}
		},
		cognome : {
			validators : {
				notEmpty : {
					message : 'Inserisci cognome'
				},
				regexp : {
					regexp : /^[A-Z a-z]+$/,
					message : 'Cognome non valido'
				},
			}
		},
		email : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email'
				},
				regexp : {
					regexp : '^[a-zA-Z0-9.]+\@unisa\.it',
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
                },
                regexp : {
					regexp : /^[0-9a-zA-Z._-]+$/,
					message : 'Formato password non valido'
				},
			}
		},
		confirmpassword : {
			validators : {
				notEmpty : {
					message : 'Inserisci conferma password'
				},
				identical : {
					field : 'password',
					message : 'La password &egrave diversa'
				}
			}
		},
		materia : {
			validators : {
				notEmpty : {
					message : 'Inserisci il corso in cui insegni'
				}
			}
		}
	}
});

$('#ajax-login-form').bootstrapValidator({
	//live: 'disabled',
	message : 'This value is not valid',
	feedbackIcons : {
		valid : 'glyphicon glyphicon-ok',
		invalid : 'glyphicon glyphicon-remove',
		validating : 'glyphicon glyphicon-refresh'
	},
	fields : {
		email : {
			validators : {
				notEmpty : {
					message : 'Inserisci l\'email'
				},
				regexp : {
					regexp :  /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
					message : 'Email non valida'
				}
			}
		},
		password : {
			validators : {
				notEmpty : {
					message : 'Inserisci la password'
				}
			}
		}
	}
});

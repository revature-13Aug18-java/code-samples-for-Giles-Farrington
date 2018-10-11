
window.onload=function(){
	window.location.replace("#");

	// slice off the remaining '#' in HTML5:    
	if (typeof window.history.replaceState == 'function') {
	  history.replaceState({}, '', window.location.href.slice(0, -1));
	}
    loadLoginView();
    
}

function loadLoginView(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            $('#view').html(xhr.responseText);
             getLoginInfo();
        }
    }
    xhr.open("GET", "login.view", true);
    xhr.send();
}

function getLoginInfo(){
	var loginUser = null;
	var xhr = new XMLHttpRequest();
	var userRole = null;
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			users = JSON.parse(xhr.responseText);
			console.log(users);
			document.getElementById("login").onclick = function(){
				for(let u of users){
					if(checkUserInfo(u)){
						loginUser = u;
						break;
					}
				}
				if(loginUser == null){
					alert("Incorrect information entered!!");
				}
				else{
					if(loginUser.user_role_id == 1){
						alert("Correct information entered!!" + " Logging in as: " + loginUser.user_first_name + " as Employee");
						loadEmployeeView(loginUser);
					}
					else{
						alert("Correct information entered!!" + " Logging in as: " + loginUser.user_first_name + " as Finance Manager");
						loadFinanceManagerView(loginUser, users);
					}
				}
			}
		}
	}
	xhr.open("GET", "login");
	xhr.send();
}


function checkUserInfo(u){
	if(u.ers_username == document.getElementById("username").value
			&& u.ers_password == document.getElementById("password").value
			&& ((u.user_role_id == 1 && document.getElementById("employee").checked)
			|| (u.user_role_id == 2 && document.getElementById("financeManager").checked))){
		if(u.user_role_id == 'employee'){
			return true;
		}
		else{
			return true;
		}
	}
	return false;
}

function loadEmployeeView(u){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			document.getElementById("employeeName").innerHTML = u.user_first_name;
			getReimbursementInfo(u);
		}
	}
	xhr.open("GET", "employee.view");
	xhr.send();
}

function getReimbursementInfo(u){
	var xhr = new XMLHttpRequest();
	var rCount = 0;
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			reimbs = JSON.parse(xhr.responseText);
			//console.log(reimbs);
			for(var r of reimbs){
				if(r.reimb_author == u.ers_users_id){
					rCount++;
					addReimbursement(r, u);
				}
			}
			//console.log(document.getElementById("reimbTable").rows.length);
			
			while(document.getElementById("reimbTable").rows.length > rCount) {
				
				document.getElementById("reimbTable").deleteRow(0);
				}
		
			document.getElementById("newReimb").onclick = function(){
				console.log("SHOULD BE CHANGING TO VISIBLE!!");
				if(document.getElementById("createNewReimb").style.display=="none")
					document.getElementById("createNewReimb").style.display="block";
				else
					document.getElementById("createNewReimb").style.display="none";
				
			}
			document.getElementById("submitTicket").onclick = function(){
				var amount = document.getElementById("amount").value.toString(); 
				var desc = document.getElementById("description").value;
				if(amount != "" && desc != "" && (document.getElementById("business").checked || document.getElementById("travel").checked || document.getElementById("medical").checked)){
					var typeNum = 0;
					if(document.getElementById("business").checked)
						typeNum = 1;
					else if(document.getElementById("travel").checked)
						typeNum = 2;
					else
						typeNum = 3;
					var request=new XMLHttpRequest();
					request.open("POST", "employee" , true);
					request.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
					request.send(amount + ">" + desc + ">" + u.ers_users_id + ">" + typeNum);
					loadEmployeeView(u);
					loadEmployeeView(u);
					
				}
			}
			document.getElementById("logout").onclick = function(){
				loadLoginView();
			}
			
		}
	}
	xhr.open("GET", "employee");
	xhr.send();
}

function addReimbursement(r, u){
	var neededType = null;
	
	//console.log("USERS IN ADD REIMBURSEMENTS FUNCTION: " + users + "USERNAME: " + users[0].ers_username);
	var row = document.createElement("tr");
	row.setAttribute('id', u.ers_users_id);
    var cell1 = document.createElement("td");
    var cell2 = document.createElement("td");
    var cell3 = document.createElement("td");
    var cell4 = document.createElement("td");
    var cell5 = document.createElement("td");
    var cell6 = document.createElement("td");
    var cell7 = document.createElement("td");
    var cell8 = document.createElement("td");
    var cell9 = document.createElement("td");
    var cell10 = document.createElement("td");
    var cell11 = document.createElement("button");
    var cell12 = document.createElement("td");
    //console.log(r + "IN ADDING REIMBURSEMENT");
    cell1.innerHTML = r.reimb_id;
    cell2.innerHTML = "$" + r.reimb_amount;
    cell3.innerHTML = r.reimb_submitted;
    cell4.innerHTML = r.reimb_resolved;
    cell5.innerHTML = r.reimb_description;
    cell6.innerHTML = r.reimb_receipt;
    for(var us of users){
		if(us.ers_users_id == r.reimb_author){
			let name = us.ers_username;
			cell7.innerHTML = name;
		}
		else if(us.ers_users_id == r.reimb_resolver){
			let name = us.ers_username;
			cell8.innerHTML = name;
		}
	}
    if(cell8.innerHTML == "" || cell8.innerHTML == "testUser")  //HAVE TO CHANGE ONCE FIX DATABASE FOR RESOLVER_ID NOT BEING SET TO 1 BY DEFAULT
    	cell8.innerHTML = "Unresolved";
    if(r.reimb_status_id == 1)
    	cell9.innerHTML = "Pending";
    else if(r.reimb_status_id == 2){
    	cell9.innerHTML = "Approved";
    }
    else
    	cell9.innerHTML = "Denied";
    console.log("REIMB TYPE ID: " + r.reimb_type_id)
    if(r.reimb_type_id == 1)
    	cell10.innerHTML = "Business";
    else if(r.reimb_type_id == 2)
    	cell10.innerHTML = "Travel";
    else
    	cell10.innerHTML = "Medical";
    cell11.innerHTML = "Approve/Deny";
    cell12.innerHTML = "Already Resolved";
    cell11.setAttribute('type', "button");
    cell11.setAttribute('class', "btn btn-danger");
    cell11.setAttribute('data-toggle', "modal");
    cell11.setAttribute('data-target', "#exampleModal");
    cell11.setAttribute('onclick', "resolveReimbursement(this)");
    cell11.setAttribute('id', u.ers_users_id);
    
    cell1.setAttribute('style', "display: none");
    cell6.setAttribute('style', "display: none");
    
    
    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    row.appendChild(cell5);
    row.appendChild(cell6);
    row.appendChild(cell7);
    row.appendChild(cell8);
    row.appendChild(cell9);
    row.appendChild(cell10);
    if(u.user_role_id == 2){
    	if(r.reimb_status_id == 1){
        	row.appendChild(cell11);
        }
        else
        	row.appendChild(cell12);
    }
    
    
    document.getElementById("reimbTable").appendChild(row);
}

function loadFinanceManagerView(u, users){
	//console.log("GETTING MANAGER VIEW FOR: " + u.ers_username);
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			$('#view').html(xhr.responseText);
			document.getElementById("financeManagerName").innerHTML = u.user_first_name;
			loadAllReimbursements(u, users);
		}
	}
	xhr.open("GET", "financeManager.view", true);
	xhr.send();
}

function loadAllReimbursements(u, users){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			reimbs = JSON.parse(xhr.responseText);
			//console.log(reimbs);
			if(window.location.hash.substr(1) == "showAll" || window.location.hash.substr(1) == ""){
				for(var r of reimbs){
					//console.log("SHOULD BE ADDING REIMBURSEMENTS!!");
					addReimbursement(r, u, users);
				}
				while(document.getElementById("reimbTable").rows.length > reimbs.length) {
					document.getElementById("reimbTable").deleteRow(0);
					}
			}
			else if(window.location.hash.substr(1) == "showUnresolved"){
				var count = 0;
				for(var r of reimbs){
					if(r.reimb_status_id == 1){
						addReimbursement(r, u, users);
						count++;
					}
					while(document.getElementById("reimbTable").rows.length > count) {
						document.getElementById("reimbTable").deleteRow(0);
						}
					
				}
			}
			document.getElementById("unresolved").onclick = function(){
				if(window.location.hash.substr(1) == "showAll" || window.location.hash.substr(1) == ""){
					loadFinanceManagerView(u, users);
				}
			}
			document.getElementById("all").onclick = function(){
				if(window.location.hash.substr(1) == "showUnresolved"){
					loadFinanceManagerView(u, users);
				}
			}
			document.getElementById("newUserAccount").onclick = function(){
				var userRole = null;
				//console.log("NEW ACCOUNT BUTTON CLICKED");
				if(document.getElementById("createNewUser").style.display=="none")
					document.getElementById("createNewUser").style.display="block";
				else
					document.getElementById("createNewUser").style.display="none";
			}
			document.getElementById("submitUser").onclick = function(){
				var username = document.getElementById("newUsername").value.toString(); 
				var password = document.getElementById("newPassword").value;
				var firstname = document.getElementById("firstname").value.toString(); 
				var lastname = document.getElementById("lastname").value;
				var email = document.getElementById("email").value;
				//console.log("check if newEmployee is checked: " + document.getElementById("newEmployee").checked);
				if(document.getElementById("newEmployee").checked){
					//console.log("SHOULD BE SETTING userRole to 1");
					userRole = 1;
				}
				else if(document.getElementById("newFinanceManager").checked){
					//console.log("SHOULD BE SETTING userRole to 2");
					userRole = 2;
				}
				for(let u of users){
					if(u.ers_username == username){
						alert("Username already taken, please enter another.");
						username = null;
					}
					if(u.user_email == email){
						alert("An account has already been made with this email, please enter a different email.");
						email = null;
					}
				}
				//console.log("SHOULD BE STARTING POST TO FinanceManager SERVLET!!");
				//console.log("username: " + username + " password: " + password + " firstname: " + firstname + " lastname: " + lastname + " email: " + email + " userRole: " + userRole);
				if(username != null && password != null && firstname != null && lastname != null && email != null && userRole != null){
					var request=new XMLHttpRequest();
					request.open("POST", "login" , true);
					request.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
					request.send(username + "," + password + "," + firstname + "," + lastname + "," + email + "," + userRole);
					loadFinanceManagerView(u, users);
					
				}
			}
			document.getElementById("logout").onclick = function(){
				loadLoginView();
			}
			
		}
			
	}
	xhr.open("GET", "financeManager");
	xhr.send();
}

function resolveReimbursement(a){
	varCorrectUser = null;
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var resolverId = a.id;
			console.log("CURRENT RESOLVER ID: " + resolverId);
			reimbs = JSON.parse(xhr.responseText);
			document.getElementById("resolveApprove").onclick = function(){
				let resolveId = a.parentNode.getElementsByTagName("td")[0].innerHTML;
				for(var r of reimbs){
					if(r.reimb_id == resolveId){
						var request=new XMLHttpRequest();
						request.open("POST", "financeManager" , true);
						request.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
						request.send(r.reimb_id + "," + '2' + "," + resolverId);
						for(let use of users){
							if(resolverId == use.ers_users_id && resolverId != 1){
								console.log("GOT CORRECT USER FOR APPROVAL: " + use.user_first_name);
								correctUser = use;
								break;
							}
						}
						$(".modal-backdrop").remove();
						console.log("LOADING NEW VIEW");
						setTimeout(function(){ console.log("SECOND VIEW LOADED!");loadFinanceManagerView(correctUser, users); }, 1500);
						break;
					}
				}
			}
			document.getElementById("resolveDeny").onclick = function(){
				let resolveId = a.parentNode.getElementsByTagName("td")[0].innerHTML;
				for(var r of reimbs){
					if(r.reimb_id == resolveId){
						var request=new XMLHttpRequest();
						request.open("POST", "financeManager" , true);
						request.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); 
						request.send(r.reimb_id + "," + '3' + "," + resolverId);
						for(let use of users){
							if(resolverId == use.ers_users_id  && resolverId != 1){
								console.log("GOT CORRECT USER FOR DENIAL: " + use.user_first_name);
								correctUser = use;
								break;
							}
						}
						$(".modal-backdrop").remove();
						console.log("LOADING NEW VIEW");
						setTimeout(function(){ console.log("SECOND VIEW LOADED!");loadFinanceManagerView(correctUser, users); }, 1500);
						break;
					}
				}
			}
			
		}
	}
	xhr.open("GET", "financeManager");
	xhr.send();
}



















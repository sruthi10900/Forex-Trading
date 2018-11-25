function formValidation()
{
    var passId = document.register.password;
    var rePass = document.register.repassword;
    var uName = document.register.username;
    var uEmail = document.register.emailId;
    var uPhone = document.register.phoneNo;
    var bankAcc = document.register.bankNo;
console.log("srvaani heyy");
    if(passid_validation(passId,7,12))
    {
        if(allLetter(uName))
        {
            if(ValidateEmail(uEmail)){
                if(validatePhone(uPhone)){
                    if(validateBankAcc(bankAcc)){
                        if(passMatch(passId,rePass))
                          return true;
                    }
                }
            }
        }
    }
    return false;
} 
function passMatch(pass,rePass){
    alert(pass)
    if(!(pass.match(rePass))){
        alert("passwords do not match");
        return false;
    }
    else
        return true;
}
function validateBankAcc(AccNum){
  var cardNoFormat = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
  if(AccNo.value.match(cardNoFormat))
      return true;
      else{
        alert("bank account format is wrong"); 
        return false;
      }
}
function validatePhone(uPhone)
{
  var phoneno = /^\d{10}$/;
  if((uPhone.value.match(phoneno)))
      return true;
      else
        return false;
}
function passid_validation(passid,mx,my){
    var passid_len = passid.value.length;
    if (passid_len == 0 ||passid_len >= my || passid_len < mx){
        alert("Password should not be empty / length be between "+mx+" to "+my);
        passid.focus();
        return false;
    }
    return true;
}
function allLetter(uname){ 
    var letters = /^[A-Za-z]+$/;
    if(uname.value.match(letters))
        return true;
    else{
        alert('Username must have alphabet characters only');
        uname.focus();
        return false;
    }
}
function ValidateEmail(uemail){
    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if(uemail.value.match(mailformat))
        return true;
    else
    {
        alert("You have entered an invalid email address!");
        uemail.focus();
        return false;
    }
}
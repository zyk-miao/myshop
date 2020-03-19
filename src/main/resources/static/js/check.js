window.onload=function () {
    checknotr();
}
function checkCooie() {
    var cookies=document.cookie.split(";");
    for (let i = 0,l=cookies.length; i <l ; i++) {
        let t=cookies[i].split("=");
        if (t[0].trim()=="login_name"){
            return true;
        }
    }
    return false;
}
function checknotr() {
    if (!checkCooie()) {
        window.location.replace("http://59.110.125.40:8080/adman/login.html");
    }

}
function getPassword() {
    var cookies=document.cookie.split(";");
    for (let i = 0,l=cookies.length; i <l ; i++) {
        let t=cookies[i].split("=");
        if (t[0].trim()=="login_password"){
            return t[1].trim();
        }
    }
    return null;
}
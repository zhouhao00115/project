/**
 * Created by Administrator on 2017/6/13.
 */
function query(){
    var companyId =  $("#conpanyId").val();
    alert(companyId);
    if("" !=companyId){
        window.location="blackCompany.do?companyId="+companyId;
    }
}
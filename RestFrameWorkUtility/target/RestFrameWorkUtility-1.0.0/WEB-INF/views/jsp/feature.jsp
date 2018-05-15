<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link href='https://fonts.googleapis.com/css?family=Merienda' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Dekko' rel='stylesheet'>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="autocompleter.js"></script>
<link href="~/Content/dynamicCheckboxes.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" rel="Stylesheet"></link>
<script src='https://cdn.rawgit.com/pguso/jquery-plugin-circliful/master/js/jquery.circliful.min.js'></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js" ></script>

<html>
<head>
<style type="text/css">
.myForm textarea {
      width: 99%;
    padding: 4px 16px;
    margin: 3px 0;
    display: inline-block;
    border: 1px  #ccc;
    box-sizing: border-box;
    font-family:"25pt Impact,Charcoal,sans-serif";
    color:solid-black;
    
  filter: alpha(opacity=40);
  background-color:#ffffff;
  margin-left: 21px;
}
input[type=text],select {
    width: 100%;
    padding: 4px 16px;
    margin: 2px 0;
    display: inline-block;
    border: 1px  #ccc;
    box-sizing: border-box;
    font-family:"25pt Impact,Charcoal,sans-serif";
    color:solid-black;
    
  filter: alpha(opacity=40);
  background-color:#ffffff;
}


</style>
<link href="<c:url value="/resources/css/osiform.css" />" rel="stylesheet">

<title>Feature File Scenario Details</title>

 <script src="/AjaxWithSpringMVC2Annotations/js/jquery.js"></script>

 <script type="text/javascript">

 function searchFileName(pname) {
   

if(pname=="featureFile"){
	var name= document.getElementById("featureFile").value;
	document.getElementById("featureradio1").disabled = true;
	document.getElementById("featureradio2").disabled = true;
}else if(pname=="stepDefFile"){
	var name= document.getElementById("stepDefFile").value;
	document.getElementById("stepdefradio1").disabled = true;
    document.getElementById("stepdefradio2").disabled = true;
}else if(pname=="requestFile"){
	var name= document.getElementById("requestFile").value;	
	document.getElementById("requestFile1").disabled = true;
    document.getElementById("requestFile2").disabled = true;
}else if(pname=="responseFile"){
	var name= document.getElementById("responseFile").value;	
	document.getElementById("responseFile1").disabled = true;
	document.getElementById("responseFile2").disabled = true;
}
    $.ajax({
                url : "findFileName",
                type : "GET",
                data: {filename: name,fieldname: pname},
                dataType : "json",
                success : function(response) {
                if(pname=="featureFile"){
               
                if(response=='')
                    {
                    document.getElementById("featureradio1").checked = true;
                    document.getElementById("featureradio2").checked = false;
                } else if(response.includes(name)){
                console.log("inside else........... ");
                document.getElementById("featureradio2").checked = true;
                            document.getElementById("featureradio1").checked = false;
                    }else{
                    document.getElementById("featureradio1").checked = true;
                            document.getElementById("featureradio2").checked = false;
                        }
                 
                    $( "#featureFile" ).autocomplete({   
                         source: response,
                         select: function (e, u) {
                            $.inArray(u.item.value, response)
                             {
                            document.getElementById("featureradio2").checked = true;
                            document.getElementById("featureradio1").checked = false;
                            }
                           }   
                   });
                }
                else if(pname=="stepDefFile"){
                if(response=='')
                {
                document.getElementById("stepdefradio1").checked = true;
                document.getElementById("stepdefradio2").checked = false;
            } else if(response.includes(name)){
        console.log("inside else........... ");
        document.getElementById("stepdefradio2").checked = true;
                    document.getElementById("stepdefradio1").checked = false;
            }else{
            document.getElementById("stepdefradio1").checked = true;
                        document.getElementById("stepdefradio2").checked = false;
                }  
                $( "#stepDefFile" ).autocomplete({   
                            source: response,
                            select: function (e, u) {
                               
                                $.inArray(u.item.value, response)
                                {
                                document.getElementById("stepdefradio2").checked = true;
                                document.getElementById("stepdefradio1").checked = false;
                               
                                    }
                              }    
                          });
                    } else if(pname=="requestFile"){
                    if(response=='')
                    {
                    document.getElementById("requestFile1").checked = true;
                    document.getElementById("requestFile2").checked = false;
                } else if(response.includes(name)){
            console.log("inside else........... ");
            document.getElementById("requestFile2").checked = true;
                        document.getElementById("requestFile1").checked = false;
                }else{
                document.getElementById("requestFile1").checked = true;
                            document.getElementById("requestFile2").checked = false;
                    }  
                    $( "#requestFile" ).autocomplete({   
                                source: response,
                                select: function (e, u) {
                                   
                                    $.inArray(u.item.value, response)
                                    {
                                    document.getElementById("requestFile2").checked = true;
                                    document.getElementById("requestFile1").checked = false;
                                   
                                        }
                                  }    
                              });
                        }else if(pname=="responseFile"){
                            if(response=='')
                            {
                            document.getElementById("responseFile1").checked = true;
                            document.getElementById("responseFile2").checked = false;
                        } else if(response.includes(name)){
                    console.log("inside else........... ");
                    document.getElementById("responseFile2").checked = true;
                                document.getElementById("responseFile1").checked = false;
                        }else{
                        document.getElementById("responseFile1").checked = true;
                                    document.getElementById("responseFile2").checked = false;
                            }  
                            $( "#responseFile" ).autocomplete({   
                                        source: response,
                                        select: function (e, u) {
                                           
                                            $.inArray(u.item.value, response)
                                            {
                                            document.getElementById("responseFile2").checked = true;
                                            document.getElementById("responseFile1").checked = false;
                                           
                                                }
                                          }    
                                      });
                                }
}
         });
}

 
 
 
        </script>


</head>



<body background="<c:url value='/resources/img/osi.jpg' />">

<form:form method="POST" class="myForm" action="addFeature" name="featureForm">
<h1 align="center"   style="font:17pt Seri Bold SC;color:white;font-weight:Bold"> Feature File Generation</h1>
<table align="center"  >
<tr>
<td style="color:white;font-weight:Bold"><form:label path="scenario">Scenario<span style="color:red">*</span></form:label></td>
<td><form:input path="scenario"  required="required"/></td>
</tr>
<tr>
<td style="color:white;font-weight:Bold"><form:label path="requestMethod">Request Method</form:label></td>
  <td> <select name="requestMethod">
 
<option value="GET">GET</option>
                 <option value="POST">POST</option>
                 <option value="PUT">PUT</option>
                 <option value="PATCH">PATCH</option>
                 <option value="DELETE">DELETE</option>
                 <option value="OPTIONS">OPTIONS</option>
               </select></td>
</tr>
<tr>
<td style="color:white;font-weight:Bold"><form:label path="uri">URI<span style="color:red">*</span></form:label></td>
<td><form:input path="uri"  required="required"/></td>
</tr>

<tr>
<td style="color:white;font-weight:Bold"><form:label path="pathParameters">Path Parameters</form:label></td>
<td><form:input path="pathParameters" /></td>
</tr>

<tr>
<td style="color:white;font-weight:Bold"><form:label path="queryParameters">Query Parameters</form:label></td>
<td><form:input path="queryParameters" /></td>
</tr>

<tr>
<td style="color:white;font-weight:Bold"><form:label path="headerParameters">Header Parameters</form:label></td>
<td><form:input path="headerParameters" /></td>
</tr>

<tr>
<td style="color:white;font-weight:Bold"><form:label path="requestFile">Request File</form:label></td>
<td><form:input path="requestFile" id="requestFile" name="requestFile" onkeyup="searchFileName('requestFile');"/></td>
</tr>
<tr>
<td></td>
<td style="color:white;font-weight:Bold"><form:radiobutton path="createORappendRQF" id="requestFile1" value="newFile" checked="checked"/>create
    <form:radiobutton path="createORappendRQF" id="requestFile2" value="existedFile"/>append</td>
</tr> 
<tr>
<td style="color:white;font-weight:Bold"><form:label path="requestFileData">Request body</form:label></td>
<td><form:textarea path="requestFileData" /></td>
</tr>

<tr>
<td style="color:white;font-weight:Bold"><form:label path="responseFile">Response File</form:label></td>
<td><form:input path="responseFile" id="responseFile" name="responseFile" onkeyup="searchFileName('responseFile');"/></td>
</tr>
<tr>
<td></td>
<td style="color:white;font-weight:Bold"><form:radiobutton path="createORappendRF"  id="responseFile1" value="newFile" checked="checked"/>create
    <form:radiobutton path="createORappendRF" id="responseFile2" value="existedFile"/>append</td>
</tr> 
<tr>
<td style="color:white;font-weight:Bold"><form:label path="responseFileData">Response body</form:label></td>
<td><form:textarea path="responseFileData" /></td>
</tr>

<tr>
<td style="color:white;font-weight:Bold"><form:label for="featureFile" path="featureFile">Feature File Name<span style="color:red">*</span></form:label></td>
<td><form:input id="featureFile" path="featureFile" name="featureFile"  required="required" onkeyup="searchFileName('featureFile');"/></td>
</tr>
<tr>
<td></td>
 
                <td style="color:white;font-weight:Bold"><form:radiobutton path="createORappendFF" id="featureradio1" value="create" checked="checked"/>create
                      <form:radiobutton path="createORappendFF" id="featureradio2" value="append"/>append</td>
             </tr>
<tr>
<td style="color:white;font-weight:Bold"><form:label for="stepDefFile" path="stepDefFile">StepDef File Name<span style="color:red">*</span></form:label></td>
<td><form:input id="stepDefFile" path="stepDefFile" name="stepDefFile"  required="required" onkeyup="searchFileName('stepDefFile');"/></td>
</tr>
<tr>
<td></td>
<td style="color:white;font-weight:Bold"><form:radiobutton path="createORappendSD" id="stepdefradio1" value="create" checked="checked"/>create
    <form:radiobutton path="createORappendSD" id="stepdefradio2" value="append"/>append</td>
</tr> 


<tr>
<td colspan="2" align="center"><input type="submit" value="Submit" /></td>
</tr>

</table>


</form:form>
</body>

</html>

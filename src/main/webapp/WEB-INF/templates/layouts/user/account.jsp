<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row" id="content_bg" >
                
                <div id="acc_menu" class="col-md-3 col-md-offset-1">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">User</div>
                        </div>
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked" id="nav_bar">
                                <li>
                                    <a href="#personal_info_tab" data-toggle="tab" class="btn btn-default">Personal Info</a>
                                </li>
                                <li>
                                    <a href="#account_info_tab" data-toggle="tab" class="btn btn-default">Account Settings</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">Rating</div>
                        </div>
                        <div class="panel-body">
                            <div class="progress">
                                <div class="progress-bar" role="progressbar" aria-valuenow="${person.generalRating}" aria-valuemin="0" aria-valuemax="100" style="width: ${person.generalRating}%;">
                                    <span class="sr-only">${person.generalRating}%</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div id="acc_edit" class="col-md-7">

                    <div id="tab_content" class="tab-content">
                    <div id="personal_info_tab" class="tab-pane fade active in">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="panel-title">Personal Info</div>
                            </div>
                            <div class="panel-body">
                                <form id="info_form"  action="${pageContext.request.contextPath}/account/updatePersonalInfo" role="form" method="post">
                                    <div class="form-group">

                                        <label for="name_input">Name</label>
                                        <input name="name" type="text" maxlength="30"  class="form-control" id="name_input" placeholder="Name" value="${person.name}" />

                                    </div> 
                                    <div class="form-group">
                                        <label for="surname_input">Surname</label>
                                        
                                        <input name="surname" type="text" maxlength="30" name="surname" class="form-control" id="surname_input" placeholder="Surname" value="${person.surname}" />
                                        
                                    </div>
                                    <div class="form-group">
                                        <label for="phone_input">Phone Number</label>
                                        
                                        <input id="acc_cellphone" name="cellphone" type="text" maxlength="13" name="cellphone" class="form-control" id="phone_input" placeholder="Phone Number" value="${person.cellphone}"/>
                                        
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <c:choose>
                                                <c:when test="${person.sms}">
                                                    <input class="checkbox" type="checkbox"  name="sms" checked="checked" > sms notification
                                                </c:when>
                                                <c:otherwise>
                                                    <input class="checkbox" type="checkbox"  name="sms" checked="checked" > sms notification
                                                </c:otherwise>
                                            </c:choose>
                                            
                                        </label>
                                    </div>
                                        <button id="info_submit" type="submit" class="btn btn-success">Save</button>
                                        <div class="alert alert-danger hide" id="info_error"></div>
                                        <div class="alert alert-success hide" id="info_success"></div>
                                </form>
                            </div>
                        </div>
                    </div>



                    <div id="account_info_tab" class="tab-pane fade"> 
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="panel-title">Password Change</div>
                            </div>
                            <div class="panel-body">
                                <form id="pass_form" action="${pageContext.request.contextPath}/account/changePassword" role="form" method="post">
                                    <div class="form-group">

                                        <label for="old_pass_input">Old Password</label>
                                        
                                        <input name="oldPass" type="password" minLength="6" maxlength="16" class="form-control" id="old_pass_input" placeholder="Old Password" />
                                        
                                    </div> 
                                    <div class="form-group">
                                        <label for="new_pass_input">New Password</label>
                                        
                                        <input id="reg_pass" name="newPass" type="password" minLength="6" maxlength="16"  class="form-control" id="new_pass_input" placeholder="New Password" />
                                        
                                    </div>
                                    <div class="form-group">
                                        <label for="r_new_pass_input">Re-Enter Password</label>
                                        
                                        <input name="rNewPass" equalto="#reg_pass" type="password" minLength="6" maxlength="16"  class="form-control" id="r_new_pass_input" placeholder="Re-Enter Password" />
                                        
                                        
                                    </div>
                                        <button type="submit" class="btn btn-warning">Change</button>
                                        <div class="alert alert-danger hide" id="pass_error"></div>
                                        <div class="alert alert-success hide" id="pass_success"></div>
                                </form>
                            </div>
                        </div>

                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <div class="panel-title">Email Change</div>
                            </div>
                            <div class="panel-body">
                                <form id="email_form" role="form" action="${pageContext.request.contextPath}/account/changeEmail" method="post">
                                    <div class="form-group">

                                        <label for="email_input">New Email</label>

                                        <input type="email" maxlength="30" name="email" class="form-control" id="email_input" placeholder="New Email">

                                    </div> 
                                    <button type="submit" class="btn btn-warning">Change</button>
                                    <div class="alert alert-danger hide" id="email_error"></div>
                                    <div class="alert alert-success hide" id="email_success"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
                    
                
            </div>
      
            
            <!--<div class="col-md-2" id="left_main">
                    
                </div>
                
                <div class="col-md-8" id="center_main">
                    <div class="row">
                        <div class="col-md-3" id="acc_img_graph_wrapper">
                            <div class="row" id="acc_img_wrapper">
                                <img class="img-thumbnail" src="${pageContext.request.contextPath}/resources/img/freebsd.jpg" style="width: 200px; margin: 0 20px 0 20px;">
                            </div>
                            <div class="row">
                                <button class="btn-xs" id="acc_img_button">Change</button>
                            </div>
                            <div class="row" id="acc_graph_wrapper" style="margin-top: 15px">

                                    <div class="panel panel-info">
                                        <div class="page-header text-primary">
                                            Your Ration
                                        </div>
                                        <div class="panel-body">
                                            <div class="progress">
                                                <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="${person.getGeneralRating()}" aria-valuemin="0" aria-valuemax="100" style="width: ${person.getGeneralRating()}%">
                                                    <span class="sr-only">40% Complete (success)</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </div>
                        </div>
                                                    
                        <div class="col-md-9" id="acc_user_info_wrapper">
                             <ul class="list-unstyled">
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Email</div>   
                                        <div class="col-md-6">
                                            <input id="email_edit" type="text" class="form-control hide" placeholder="${person.getEmail()}">
                                            <div id="email_text" class="text-info">${person.getEmail()}</div>
                                            <div id="error_div" class="alert alert-danger hide">
                            
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            
                                            <button class="btn-xs" id="acc_email_button">Change</button>
                                            
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Name</div>   
                                        <div class="col-md-6">
                                            <input id="name_edit" type="text" class="form-control hide" placeholder="${person.getName()}">
                                            <div id="name_text" class="text-info">${person.getName()}</div>
                                        </div>
                                        <div class="col-md-3"><button class="btn-xs" id="acc_name_button">Change</button></div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Surname</div>   
                                        <div class="col-md-6">
                                            <input id="surname_edit" type="text" class="form-control hide" placeholder="${person.getSurname()}">
                                            <div id="surname_text" class="text-info">${person.getSurname()}</div>
                                        </div>
                                        <div class="col-md-3"><button class="btn-xs" id="acc_surname_button">Change</button></div>
                                    </div>
                                </li>
                                
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Phone</div> 
                                        
                                        <div class="col-md-6">
                                            <input id="phone_edit" type="text" class="form-control hide" placeholder="${person.getCellphone()}">
                                            <div id="phone_text" class="text-info">${person.getCellphone()}</div>
                                        </div>
                                        <div class="col-md-3"><button class="btn-xs" id="acc_phone_button">Change</button></div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="row">
                                        <div class="col-md-3">Password</div>   
                                        <div class="col-md-9">
                                         
                                                <input id="old_pass" type="password" name="oldPass" class="form-control hide" placeholder="Old Password">
                                                <input id="new_pass" type="password" name="newPass" class="form-control hide" placeholder="New Password">
                                                <input id="re_new_pass" type="password" name="rNewPass" class="form-control hide" placeholder="Re_enter New Password">
                                                <div id="error_pass_div" class="alert alert-danger hide">
                            
                                                </div>
                                                <button class="btn-xs" id="acc_password_button">Change Password</button>
                                            
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                                                  
                    </div>
                </div>
                <div class="col-md-2" id="left_main">
                    
                </div>-->
                
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <div class="row">
                
                <div class="col-md-1" id="left_main">
                    <!--New Arrivals-->
                </div>
                
                <div class="col-md-11" id="center_main">
                    <div class="row">
                        <div class="col-md-3" id="acc_img_graph_wrapper">
                            <div class="row" id="acc_img_wrapper">
                                <img class="img-thumbnail" src="${pageContext.request.contextPath}/resources/img/freebsd.jpg">
                            </div>
                            <div class="row">
                                <button class="btn-xs" id="acc_img_button">Change</button>
                            </div>
                            <div class="row" id="acc_graph_wrapper">
                                Area For Ratio Graph
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
                                            
                                            <input id="old_pass" type="text" class="form-control hide" placeholder="Old Password">
                                            <input id="new_pass" type="text" class="form-control hide" placeholder="New Password">
                                            <input id="re_new_pass" type="text" class="form-control hide" placeholder="Re_enter New Password">
                                            <div id="pass_changed" class="text-info hide">Password Changed</div>
                                            <button class="btn-xs" id="acc_password_button">Change Password</button>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                
            </div>
          
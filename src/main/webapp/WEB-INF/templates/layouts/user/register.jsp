<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

        <div class="row well">
            <div class="col-md-4 col-md-offset-4" style="margin-top: 50px;">

                <div class="panel panel-success">
                    <div class="panel-heading">
                        <div class="panel-title">Registration Form</div>
                         </div>
                    <div class="panel-body">
                        <div class="form-group col-lg-10">
                            <form id="registration_form" class="form-horizontal" role="form" >
                        <legend><spring:message code="message.registration" /></legend>
                        <div class="form-group ">
                            <input id="reg_name" class="form-control" type="text" name="name" minlength="2" placeholder="<spring:message code="message.name" />">
                        </div>
                        <div class="form-group ">
                            <input id="reg_surname" class="form-control" type="text" name="surname"  placeholder="<spring:message code="message.surname" />">
                        </div>
                        <div class="form-group ">
                            <input id="reg_mail" class="form-control" type="email" name="email" required placeholder="Email">
                        </div>
                        <div class="form-group ">
                            <input id="reg_rmail" class="form-control" equalto="#reg_mail" type="email" name="rEmail" required placeholder="<spring:message code="message.reenter" /> Email">
                        </div>
                        <div class="form-group ">
                            <input id="reg_pass" class="form-control" type="password" name="password" required placeholder="Password">
                        </div>
                        <div class="form-group ">
                            <input id="reg_rpass" class="form-control" equalto="#reg_pass" type="password" name="rPassword" required placeholder="<spring:message code="message.reenter" /> Password">
                        </div>
                        <div class="form-group ">
                            <input id="reg_phone" class="form-control" type="text" name="cellPhone" required placeholder="<spring:message code="message.phone" />">
                        </div>
                        <div class="form-group">
                        <button id="form_submit" class="btn btn-success" url="${pageContext.request.contextPath}/register" type="submit" ><spring:message code="message.registration" /></button>
                        <div class="clearfix"></div>
                        <div id="error_div" class="alert alert-danger hide">
                            
                        </div>
                        </div>
                        
                    </form>
                        
                     <!--Modal Success Reg-->
                    
                    <div class="modal fade" id="success_reg" tabindex="-1" role="dialog" aria-labelledby="succes_reg_label" aria-hidden="true">
                                        <div class="modal-dialog">
                                          <div class="modal-content">
                                            <div class="modal-header">
                                              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                              <h4 class="modal-title" id="success_reg_label">Registration Notification</h4>
                                            </div>
                                            <div class="modal-body">
                                              <h4>You successfully register!</h4>
                                              <p>
                                              <h4>Check your email for confirmation</h4>
                                            </div>
                                            
                                            </div>
                                          </div>
                                        </div>
                                </div>
                    <!--Modal Success Reg--> 
                        </div>
                    </div>
                </div>
                </div>
        
        


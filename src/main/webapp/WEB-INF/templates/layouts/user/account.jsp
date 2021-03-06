<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row well" >
	<div id="path" url="${pageContext.request.contextPath}"></div>

	<div id="acc_menu" class="col-md-3 col-md-offset-1">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title"><spring:message code="message.user" /></div>
			</div>
			<div class="panel-body">
				<ul class="nav nav-pills nav-stacked" id="nav_bar">
					<li><a href="#personal_info_tab" data-toggle="tab"
						class="btn btn-default"><spring:message code="message.personal" /></a></li>
					<li><a href="#account_info_tab" data-toggle="tab"
						class="btn btn-default"><spring:message code="message.accsettins" /></a></li>
				</ul>
			</div>

		</div>

		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title"><spring:message code="message.rating" /></div>
			</div>
			<div class="panel-body">
				<div class="progress">
					<div class="progress-bar" role="progressbar"
						aria-valuenow="${person.generalRating}" aria-valuemin="0"
						aria-valuemax="100" style="width: ${person.generalRating}%;">
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
						<div class="panel-title"><spring:message code="message.personal" /></div>
					</div>
					<div class="panel-body">
						<form id="info_form"
							action="${pageContext.request.contextPath}/account/updatePersonalInfo"
							role="form" method="post">
							<div class="form-group">

								<label for="name_input"><spring:message code="message.libFirstName" /></label> <input name="name"
									type="text" maxlength="30" class="form-control" id="name_input"
									placeholder="Name" value="${person.name}" />

							</div>
							<div class="form-group">
								<label for="surname_input"><spring:message code="message.libLastName" /></label> <input name="surname"
									type="text" maxlength="30" name="surname" class="form-control"
									id="surname_input" placeholder="Surname"
									value="${person.surname}" />

							</div>
							<div class="form-group">
								<label for="phone_input"><spring:message code="message.libCellPhone" /></label> <input
									id="acc_cellphone" name="cellphone" type="text" maxlength="13"
									name="cellphone" class="form-control" id="phone_input"
									placeholder="Phone Number" value="${person.cellphone}" />

							</div>
							<div class="checkbox">
								<label> <c:choose>
										<c:when test="${person.sms}">
											<input class="checkbox" type="checkbox" name="sms"
												checked="checked"> <spring:message code="message.libSMS" />
                                                </c:when>
										<c:otherwise>
											<input class="checkbox" type="checkbox" name="sms"
												> <spring:message code="message.libSMS" />
                                                </c:otherwise>
									</c:choose>

								</label>
							</div>
							<button id="info_submit" type="submit" class="btn btn-success"><spring:message code="message.save" /></button>
							<div class="alert alert-danger hide" id="info_error"></div>
							<div class="alert alert-success hide" id="info_success"></div>
						</form>
					</div>
				</div>
			</div>



			<div id="account_info_tab" class="tab-pane fade">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title"><spring:message code="message.passchange" /></div>
					</div>
					<div class="panel-body">
						<form id="pass_form"
							action="${pageContext.request.contextPath}/account/changePassword"
							role="form" method="post">
							<div class="form-group">

								<label for="old_pass_input">Old Password</label> <input
									name="oldPass" type="password" minLength="6" maxlength="16"
									class="form-control" id="old_pass_input"
									placeholder="Old Password" />

							</div>
							<div class="form-group">
								<label for="new_pass_input">New Password</label> <input
									id="reg_pass" name="newPass" type="password" minLength="6"
									maxlength="16" class="form-control" id="new_pass_input"
									placeholder="New Password" />

							</div>
							<div class="form-group">
								<label for="r_new_pass_input">Re-Enter Password</label> <input
									name="rNewPass" equalto="#reg_pass" type="password"
									minLength="6" maxlength="16" class="form-control"
									id="r_new_pass_input" placeholder="Re-Enter Password" />


							</div>
							<button type="submit" class="btn btn-warning"><spring:message code="message.change" /></button>
							<div class="alert alert-danger hide" id="pass_error"></div>
							<div class="alert alert-success hide" id="pass_success"></div>
						</form>
					</div>
				</div>

				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title"><spring:message code="message.emailchange" /></div>
					</div>
					<div class="panel-body">
						<form id="email_form" role="form"
							action="${pageContext.request.contextPath}/account/changeEmail"
							method="post">
							<div class="form-group">

								<label for="email_input">New Email</label> <input type="email"
									maxlength="30" name="email" class="form-control"
									id="email_input" placeholder="New Email">

							</div>
							<button type="submit" class="btn btn-warning"><spring:message code="message.change" /></button>
							<div class="alert alert-danger hide" id="email_error"></div>
							<div class="alert alert-success hide" id="email_success"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</div>

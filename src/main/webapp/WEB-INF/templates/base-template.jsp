<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<!-- TODO: files should define only taglibs they use -->
<!-- TODO: some files in this folder does not look like templates, should be moved to /views -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        
        <tiles:insertAttribute name="style-js-main" />
        <tiles:insertAttribute name="style-js" />
        

    </head>
    <body>
        <div class="container-full">
            <div class="row">
                <tiles:insertAttribute name="header" />
            </div>

           
            <tiles:insertAttribute name="content" />

            <tiles:insertAttribute name="footer" />
        </div>
    </body>
</html>
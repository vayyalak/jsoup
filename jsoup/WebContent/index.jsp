<!doctype html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%String context=request.getContextPath();%>
 
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%=context %>/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
	<script src="<%=context %>/js/jquery-1.10.2.js"></script>
	<script src="<%=context %>/js/jquery-ui-1.10.4.custom.js"></script>

<style type="text/css">
			body {
				font: 62.5% "Trebuchet MS", sans-serif;
				margin: 50px;
			}
			
			.demoHeaders {
				margin-top: 2em;
			}
			
			#dialog-link {
				padding: .4em 1em .4em 20px;
				text-decoration: none;
				position: relative;
			}
			
			#dialog-link span.ui-icon {
				margin: 0 5px 0 0;
				position: absolute;
				left: .2em;
				top: 50%;
				margin-top: -8px;
			}
			
			#icons {
				margin: 0;
				padding: 0;
			}
			
			#icons li {
				margin: 2px;
				position: relative;
				padding: 4px 0;
				cursor: pointer;
				float: left;
				list-style: none;
			}
			
			#icons span.ui-icon {
				float: left;
				margin: 0 4px;
			}
			
			.fakewindowcontain .ui-widget-overlay {
				position: absolute;
			}
	</style>

</head>
<body>
  <form name='getlinks' action="main" method="get">
     <button id="button" type="submit">Get Reports</button>
  </form>
</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
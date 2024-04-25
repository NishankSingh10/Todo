
<%@ include file="common/header.jspf"%>

<div>

	<pre>${errorMsg}</pre>
	<h1>Welcome to login page!</h1>

	<form method="post">
		Name:<input type="text" name="name" placeholder="Name" /><br>
		Password:<input type="password" name="password" placeholder="Password" /><br>
		<input type="submit">

	</form>

</div>

<%@ include file="common/footers.jspf"%>
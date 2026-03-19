<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<div class="navbar">
		<h2>HMS</h2>
	</div>

	<div class="container">
		<div class="card">

			<h2>Login</h2>

			<form action="login" method="post">

				<div class="form-group">
					<label>Email</label>
					<input type="email" name="email" required>
				</div>

				<div class="form-group">
					<label>Password</label>
					<input type="password" name="password" required>
				</div>

				<button type="submit">Login</button>

			</form>

		</div>
	</div>

</body>
</html>
<!DOCTYPE html>

<html>
	<head><title>Waktu</title>
	<body>
		<c:choose>
			<c:when test="${user != null}">
				<p>
					Welcome, ${user.email} !!
					<a href="${logouturl}">Logout</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>
					Welcome!
					<a href="${loginurl}">Sign On</a>
				</p>
			</c:otherwise>
		</c:choose>
		<p> Time is : ${currentTime}</p>
	</body>
</html>
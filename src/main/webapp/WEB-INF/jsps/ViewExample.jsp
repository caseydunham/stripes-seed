<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<s:layout-render name="Layout.jsp" pageTitle="home">
	<s:layout-component name="pageBody">
		<c:choose>
			<c:when test="${actionBean.example == null}">
				<h1><strong>Example Not Found</strong></h1>
			</c:when>
			<c:otherwise>
				<h1><strong><c:out value="${actionBean.example.title}"/></strong></h1>
				<p>
					<i class="icon-user"></i> by <c:out value="${actionBean.example.username}"/> | <i class="icon-calendar"></i> <s:format value="${actionBean.example.created}" formatPattern="EEE, dd MMM HH:mm"/>
				</p>
				<p></p>
				<pre><c:out value="${actionBean.example.content}"/></pre>
			</c:otherwise>
		</c:choose>
	</s:layout-component>
</s:layout-render>
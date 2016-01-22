<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<s:layout-render name="Layout.jsp" pageTitle="home">
	<s:layout-component name="pageBody">
		<s:form action="/example" beanclass="net.caseydunham.seed.stripes.action.HomeActionBean" id="shareExampleForm">
			<fieldset>
				<legend>Create Example</legend>
				<s:errors/>
				<input type="text" id="title" name="title" class="input-block-level" placeholder="Enter a title. Will default to Untitled if left blank."/>
				<input type="text" id="username" name="username" class="input-block-level" placeholder="Enter a username. Will default to Unknown if left blank."/>
				<textarea id="content" name="content" rows="10" cols="50" class="input-block-level" placeholder="Content"></textarea>
				<s:submit name="submit" class="btn btn-primary pull-right" value="Share"/>
			</fieldset>
		</s:form>
	</s:layout-component>
</s:layout-render>
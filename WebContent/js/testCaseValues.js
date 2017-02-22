function getTestCaseValues(machine,versionBuild){
	var req = new XMLHttpRequest();
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			// check for HTTP status of OK
			if (req.status == 200) {
				try {
					var job=JSON.parse(req.responseText);
					//Set Result for Version Run
					var testCase=job["testCase"];
					var table="<thead ><tr  id='tableHeader'>" +
					"<th><input type='checkbox' id='selectAll'/></th><th>No</th><th>Test Case</th><th>Status</th>" +
					"<th>Comments</th><th>History</th>" +
					"</tr></thead>";
					var i=1;
					table+="<tbody>";

					for(key in testCase){
						table+="<tr>" +
						"<td><input type='checkbox' value='"+i+"'/></td><td>"+i+"</td><td>"+key+"</td>" +
						"<td class='status' rel='"+testCase[key][0]+"'>"+testCase[key][0]+"</td><td>"+testCase[key][1]+"</td>" +
						"<td><input type='image' src='assets/icons/history.jpg' " +
						"onclick=getTestCaseValues()></td>"+
						"</tr>";
						i++;
					}
					table+="</tbody>";

					//Set Value for respective Field in table
					document.getElementById("tableHeading").innerHTML="Machine ::"+machine;
					document.getElementById("machineTable").innerHTML=table;
					document.getElementById("filter").removeAttribute("style");

				} catch (e) {
					console.log("Exception::-"+e.toString());
				}
			}
		}
	};
	req.open("GET", "GetTestCaseDataServlet?machine="+machine+"&versionBuild="+versionBuild, true);
	req.send(null);

}
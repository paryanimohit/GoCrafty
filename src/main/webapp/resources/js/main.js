/**
 * 
 */
window.scrollSmooth = function(target) {
    var scrollContainer = target;
    do { //find scroll container
        scrollContainer = scrollContainer.parentNode;
        if (!scrollContainer) return;
        scrollContainer.scrollTop += 1;
    } while (scrollContainer.scrollTop == 0);

    var targetY = 0;
    do { //find the top of target relatively to the container
        if (target == scrollContainer) break;
        targetY += target.offsetTop;
    } while (target = target.offsetParent);

    scroll = function(c, a, b, i) {
        i++; if (i > 30) return;
        c.scrollTop = a + (b - a) / 30 * i;
        setTimeout(function(){ scroll(c, a, b, i); }, 20);
    }
    // start scrolling
    scroll(scrollContainer, scrollContainer.scrollTop, targetY, 0);
}

function showForm(){
	
	var deleteButton = document.getElementById("ppedit");
	if(deleteButton.style.visibility == "hidden"){
		deleteButton.style.visibility = "visible";
	}
}

function hideForm(){
	
	var deleteButton = document.getElementById("ppedit");
	if(deleteButton.style.visibility == "visible"){
		deleteButton.style.visibility = "hidden";
	}
	
	var hide = document.getElementById("deleteForm");
	if(hide.style.visibility == "visible"){
		hide.style.visibility = "hidden";
	}
}

function showVideoUploadForm(){
	
	var id = document.getElementById("uploadVideoButton");
	
}
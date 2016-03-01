var pass_hidden = false;

function view_pass() {
	var open;
	var closed;
	pass_hidden = !pass_hidden;
	if (pass_hidden) {
		open = "uneye";
		closed = "eye";
	} else {
		open = "eye";
		closed = "uneye";
	}

	var button_open = document.getElementById(open);
	button_open.style.visibility = "visible";

	var button_closed = document.getElementById(closed);
	button_closed.style.visibility = "hidden";
}

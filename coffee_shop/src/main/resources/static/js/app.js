function eerieGlow(element) {
    element.classList.add("eerie_glow");
}

function makeActive(element) {
	element.classList.add("active_box");
}

function removeGlow(element){
    element.classList.remove("eerie_glow");
}


function deActivate(element) {
	element.classList.remove("active_box");
}
function showCustomMenu(nameOfItem) {
    var nameOfItem = document.getElementById(nameOfItem);
    
    nameOfItem.classList.remove("hidden");
}

function hideCustomMenu(nameOfItem) {
    var nameOfItem = document.getElementById(nameOfItem);
    
    nameOfItem.classList.add("hidden");

}


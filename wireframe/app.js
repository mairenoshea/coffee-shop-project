function eerieGlow(element) {
    element.classList.add("eerie_glow");
}

function removeGlow(element){
    element.classList.remove("eerie_glow");
}

function showCustomMenu(nameOfItem) {
    var nameOfItem = document.getElementById(nameOfItem);
    
    nameOfItem.classList.remove("hidden");
}

function hideCustomMenu(nameOfItem) {
    var nameOfItem = document.getElementById(nameOfItem);
    
    nameOfItem.classList.add("hidden");

}
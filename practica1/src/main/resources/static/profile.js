function toggleEditMode() {

    const form = document.getElementById("profileForm");
    const editButton = document.getElementById("editButton");
    const saveButton = document.getElementById("saveButton");


    // Obtener todos los inputs dentro del formulario
    const inputs = form.querySelectorAll("input");

    if (editButton.classList.contains("d-none")) {
        // Entrando en guardar: Deshabilitar inputs
        inputs.forEach(input => input.setAttribute("disabled", true));

        editButton.classList.remove("d-none");
        saveButton.classList.add("d-none");
        
        
        alert("Cambios guardados correctamente");
    } else {
        // Entrando en  edición: Habilitar inputs
        inputs.forEach(input => input.removeAttribute("disabled"));
        editButton.classList.add("d-none");
        saveButton.classList.remove("d-none");
        
    }

}

// Esperar a que el DOM cargue
document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("editButton").addEventListener("click", toggleEditMode);
    document.getElementById("saveButton").addEventListener("click", function(event) {
        event.preventDefault(); // Evita que el formulario se envíe
        toggleEditMode();
    });
});
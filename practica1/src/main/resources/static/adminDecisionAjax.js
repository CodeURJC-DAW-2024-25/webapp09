document.body.addEventListener('click', async function (event) {
    const button = event.target.closest("button");
    console.log(button);
    if (!button) return; // If it's not a button, do nothing

    if (button.classList.contains('btn-success') || button.classList.contains('btn-danger')) {
        
        if (button.closest('.col-lg-4')) {
            // House action
            const houseColumn = button.closest(".col-lg-4");
            const houseId = houseColumn.getAttribute('data-house-id');

            if (button.classList.contains('btn-success')) {
                await acceptHouse(houseId);
            } else {
                await denyHouse(houseId);
            }

            houseColumn.remove(); // Remove from page
        } else if (button.closest('tr')) {
            // Reservation action
            const reservationRow = button.closest('tr');
            const reservationId = reservationRow.getAttribute('data-reservation-id');
            console.log(reservationId  );

            if (button.classList.contains('btn-success')) {
                await acceptReservation(reservationId);
            } else {
                await denyReservation(reservationId);
            }

            reservationRow.remove(); // Remove from page
        }
    }
});


async function acceptHouse(houseId) {
    await fetch(`/api/admin/houses/decision/${houseId}`, { method: 'PUT' });
}

async function denyHouse(houseId) {
    await fetch(`/denyHouse/${houseId}`, { method: 'DELETE' });
    await fetch(`/api/admin/houses/decision/${houseId}`, { method: 'DELETE' });
}

// Function to accept a reservation
async function acceptReservation(reservationId) {
    await fetch(`/acceptReservation/${reservationId}`, { method: 'POST' });
    await fetch(`/api/admin/reservations/decision/${reservationId}`, { method: 'PUT' });

}

// Function to deny a reservation
async function denyReservation(reservationId) {
    await fetch(`/denyReservation/${reservationId}`, { method: 'DELETE' });
    await fetch(`/api/admin/reservations/decision/${reservationId}`, { method: 'DELETE' });

}
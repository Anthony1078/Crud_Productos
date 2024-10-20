document.addEventListener('DOMContentLoaded', function() {
    cargarProductos();
});

function cargarProductos() {
    fetch('/productos/api')
    .then(response => response.json())
    .then(data => {
        const table = document.getElementById('productosTable').getElementsByTagName('tbody')[0];
        table.innerHTML = '';
        data.forEach(producto => {
            let row = table.insertRow();
            row.insertCell(0).innerHTML = producto.id;
            row.insertCell(1).innerHTML = producto.nombre;
            row.insertCell(2).innerHTML = producto.precio;
            let actionsCell = row.insertCell(3);
            actionsCell.innerHTML = `<button onclick="confirmarEliminacion(${producto.id})" class="btn btn-danger">🗑️</button>
                                     <button onclick="mostrarModalEditar(${producto.id}, '${producto.nombre}', ${producto.precio})" class="btn btn-success">✏️</button>`;
        });
    });
}

function confirmarEliminacion(productId) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: "Está seguro de eliminar el producto con ID: " + productId,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
        if (result.value) {
            eliminarProducto(productId);
        }
    });
}

function eliminarProducto(productId) {
    fetch(`/productos/api/${productId}`, { method: 'DELETE' })
    .then(() => cargarProductos());
}

function mostrarModalEditar(id, nombre, precio) {
    document.getElementById('productId').value = id;
    document.getElementById('productName').value = nombre;
    document.getElementById('productPrice').value = precio;
    $('#editModal').modal('show');
}

function updateProduct() {
    const productId = document.getElementById('productId').value;
    const updatedProduct = {
        nombre: document.getElementById('productName').value,
        precio: parseFloat(document.getElementById('productPrice').value)
    };
    fetch(`/productos/api/${productId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedProduct)
    })
    .then(() => {
        $('#editModal').modal('hide');
        cargarProductos();
    });
}

function addNewProduct() {
    const newProduct = {
        nombre: document.getElementById('newProductName').value,
        precio: parseFloat(document.getElementById('newProductPrice').value)
    };

    fetch('/productos/api', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newProduct)
    })
    .then(() => {
        $('#nuevoProductoModal').modal('hide');
        cargarProductos();
    });
}


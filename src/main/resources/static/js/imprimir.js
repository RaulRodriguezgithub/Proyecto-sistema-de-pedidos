document.getElementById('btn-pdf').addEventListener('click', () => {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    // 1️⃣ Cargar membrete
    const img = new Image();
	img.src = '/imagenes/membrete/membrete.png';  
    img.onload = () => {
        doc.addImage(img, 'PNG', 0, 0, 210, 297); // A4 mm

        // 2️ Datos cabecera
        const pedidoId = document.getElementById('pedido-id')?.textContent.trim() || '';
        const cliente  = document.getElementById('cliente-nombre')?.textContent.trim() || '';
        const fecha    = document.getElementById('pedido-fecha')?.textContent.trim() || '';
        const hora     = document.getElementById('pedido-hora')?.textContent.trim() || '';
        const estado   = document.getElementById('pedido-estado')?.textContent.trim() || '';
        const total    = document.getElementById('total-pedido')?.textContent.trim() || '';

        const pageWidth = doc.internal.pageSize.getWidth();
const centerX = pageWidth / 2;

doc.setFontSize(14);
doc.setFont(undefined, 'bold');
doc.text(`Factura del Pedido #${pedidoId}`, centerX, 25, { align: 'center' });

doc.setFontSize(11);
doc.setFont(undefined, 'normal');

// Agrupar en dos filas centradas
doc.text(`Cliente: ${cliente}`, centerX, 30, { align: 'center' });
doc.text(`Fecha: ${fecha} ${hora}   |   Estado: ${estado}`, centerX, 40, { align: 'center' });

        // 3️⃣ Tabla principal (productos)
        const bodyProductos = [];
        document.querySelectorAll('#tabla-productos tbody tr').forEach(tr => {
            const celdas = Array.from(tr.querySelectorAll('td')).map(td => td.textContent.trim());
            if (celdas.length) bodyProductos.push(celdas);
        });

        doc.autoTable({
            head: [['Producto', 'Cantidad', 'Precio Unitario', 'Subtotal']],
            body: bodyProductos,
            startY: 50
        });

        // 4️ Segunda tabla (resumen, si aplica)
        doc.autoTable({
            head: [['Concepto', 'Valor']],
            body: [
                ['Total', total]
            ],
            startY: doc.lastAutoTable.finalY + 10
        });

        // 5️⃣ Guardar PDF
        doc.save(`Factura_${pedidoId}.pdf`);
    };
});
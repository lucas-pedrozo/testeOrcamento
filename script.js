document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('budget-form');
    const addProductButton = document.getElementById('add-product');
    const productList = document.getElementById('product-list');
    const budgetList = document.getElementById('budget-list');
    let productCount = 1;

    addProductButton.addEventListener('click', () => {
        const productDiv = document.createElement('div');
        productDiv.classList.add('product');
        productDiv.innerHTML = `
            <label for="product-name-${productCount}">Nome do Produto:</label>
            <input type="text" id="product-name-${productCount}" name="productName" required>
            <label for="product-price-${productCount}">Preço:</label>
            <input type="number" id="product-price-${productCount}" name="productPrice" required>
        `;
        productList.appendChild(productDiv);
        productCount++;
    });

    form.addEventListener('submit', (e) => {
        e.preventDefault();
        const customerName = form['name'].value;
        const requestDate = form['requestDate'].value;
        const products = [];
        document.querySelectorAll('.product').forEach((product, index) => {
            const productName = product.querySelector(`input[name="productName"]`).value;
            const productPrice = product.querySelector(`input[name="productPrice"]`).value;
            products.push({ name: productName, price: productPrice });
        });

        const budgetItem = document.createElement('li');
        budgetItem.textContent = `Cliente: ${customerName}, Data: ${requestDate}, Produtos: ${products.map(p => `${p.name} - R$${p.price}`).join(', ')}`;
        budgetList.appendChild(budgetItem);

        form.reset();
        productList.innerHTML = `
            <div class="product">
                <label for="product-name-0">Nome do Produto:</label>
                <input type="text" id="product-name-0" name="productName" required>
                <label for="product-price-0">Preço:</label>
                <input type="number" id="product-price-0" name="productPrice" required>
            </div>
        `;
        productCount = 1;
    });
});

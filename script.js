document
  .getElementById("budget-form")
  .addEventListener("submit", async function (event) {
    event.preventDefault();

    const nomeCliente = document.getElementById("customer-name").value;
    const data = document.getElementById("request-date").value;

    const requestBody = {
      nomeCliente: nomeCliente,
      data: data,
    };

    try {
      const response = await fetch("http://localhost:8080/api/orcamento", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(requestBody),
      });

      const result = await response.json();

      if (!response.ok) {
        throw new Error(result.message || "Erro ao salvar orçamento");
      }

      alert("Orçamento salvo com sucesso!");

      const products = [];
      document.querySelectorAll(".product").forEach((product) => {
        const productName = product.querySelector(
          `input[name="productName"]`
        ).value;
        const productPrice = product.querySelector(
          `input[name="productPrice"]`
        ).value;
        products.push({ nome: productName, valor: productPrice });
      });

      for (let product of products) {
        const productResponse = await fetch(
          "http://localhost:8080/api/produtoOrcamento",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(product),
          }
        );

        if (!productResponse.ok) {
          console.error("Erro ao salvar produto:", product);
        } else {
          console.log("Produto salvo:", product);
        }
      }

      const budgetList = document.getElementById("budget-list");
      const listItem = document.createElement("li");

      listItem.textContent = `Cliente: ${result.nomeCliente} / Data: ${
        result.data
      } / Produtos: ${products
        .map((p) => `${p.nome} - R$${p.valor}`)
        .join(", ")}`;

      budgetList.appendChild(listItem);

      document.getElementById("budget-form").reset();
    } catch (error) {
      alert("Erro: " + error.message);
    }
  });

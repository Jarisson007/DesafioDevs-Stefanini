package com.devs.demo.Controller;

import com.devs.demo.model.Produto;
import com.devs.demo.model.QuantidadeDTO;
import com.devs.demo.model.RegistroQuantidade;
import com.devs.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        try {
            System.out.println("Antes de salvar: " + produto.toString());
            Produto novoProduto = produtoRepository.save(produto);
            System.out.println("Depois de salvar: " + novoProduto.toString());
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarPorCodigo(@PathVariable String codigo) {
        try {
            Produto produto = produtoRepository.findByCodigo(codigo);
            if (produto != null) {
                return ResponseEntity.ok(produto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{id}/quantidades")
    public ResponseEntity<String> registrarQuantidades(
            @PathVariable Long id,
            @RequestBody RegistroQuantidade registroQuantidade) {
        try {
            Optional<Produto> optionalProduto = produtoRepository.findById(id);

            if (optionalProduto.isPresent()) {
                Produto produto = optionalProduto.get();

                // Associe a nova quantidade ao produto
                registroQuantidade.setProduto(produto);

                // Adicione a nova quantidade à lista de quantidades do produto
                produto.getRegistroQuantidades().add(registroQuantidade);

                // Atualize o produto no banco de dados
                produtoRepository.save(produto);

                return ResponseEntity.ok("Quantidades registradas com sucesso para o produto de ID " + id);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
package com.devs.demo.Controller;

import com.devs.demo.model.Produto;
import com.devs.demo.model.RegistroQuantidade;
import com.devs.demo.repository.ProdutoRepository;
import com.devs.demo.service.RegistroQuantidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registro-quantidade")
public class RegistroQuantidadeController {

    @Autowired
    private RegistroQuantidadeService registroQuantidadeService;

    @Autowired
    private ProdutoRepository produtoRepository;

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

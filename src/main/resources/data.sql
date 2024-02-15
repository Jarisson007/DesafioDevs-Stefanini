INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('abc001', 'Boneca', 79.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('xyz002', 'Carrinho', 29.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('def003', 'Quebra-Cabeça', 19.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('ghi004', 'Urso de Pelúcia', 49.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('ijk005', 'Lego', 49.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('lmn006', 'Bola de Futebol', 49.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('opq007', 'Quebra-Cabeça 3D', 39.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('rst008', 'Kit de Arte', 29.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('uvw009', 'Quebra-Cabeça Infantil', 15.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('xyz010', 'Barbie Fashion', 39.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('abc011', 'Carro de Controle Remoto', 59.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('def012', 'Play-Doh Kit Criativo', 47.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('ghi013', 'Bicicleta Infantil', 129.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('tech019', 'Robô Inteligente', 129.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('drone020', 'Drone Mini Quadricóptero', 97.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('smart021', 'Tablet Educativo', 149.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('vr022', 'Óculos de Realidade Virtual para Crianças', 129.99);
INSERT INTO PRODUTO (codigo, nome, preco) VALUES ('coding023', 'Kit de Programação para Crianças', 98.99);


-- Adiciona alguns usuários
INSERT INTO usuario (nome, data_nascimento, cpf, cep) VALUES ('John Doe', '1998-01-01', '123.456.789-01', '51345-220');
INSERT INTO usuario (nome, data_nascimento, cpf, cep) VALUES ('Jane Doe', '1997-02-13','123.456.789-01', '51345-220');
INSERT INTO usuario (nome, data_nascimento, cpf, cep) VALUES ('Fernanda Silva', '1997-02-03','123.456.789-01', '51345-220');
INSERT INTO usuario (nome, data_nascimento, cpf, cep) VALUES ('Socorro neves', '1999-05-10','123.456.789-01', '51345-220');
INSERT INTO usuario (nome, data_nascimento, cpf, cep) VALUES ('Carlos silva', '1999-08-26', '123.456.789-01', '51345-220');

-- Inserir uma venda associada ao usuário e produto
INSERT INTO vendas (produto_id, cpf_usuario, quantidade, data_compra, mes)
VALUES (
(SELECT id FROM produto WHERE codigo = 'vr022 17'),
(SELECT cpf FROM usuario WHERE nome = 'John Doe'),
  5, -- Substitua pela quantidade desejada
  '2024-02-06', -- Substitua pela data desejada
  2 -- Substitua pelo mês desejado
);

-- Inserir outra venda
INSERT INTO vendas (produto_id, cpf_usuario, quantidade, data_compra, mes)
VALUES (
  (SELECT id FROM produto WHERE nome = 'abc001'),
  (SELECT cpf FROM usuario WHERE nome = 'Jane Doe'),
  3, -- Substitua pela quantidade desejada
  '2023-02-06', -- Substitua pela data desejada
  5 -- Substitua pelo mês desejado
);
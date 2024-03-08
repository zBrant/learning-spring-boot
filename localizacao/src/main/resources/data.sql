create table tb_cidade (
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome, qtd_habitantes)
values
    (1, 'Sao Paulo', 12396372),
    (2, 'Rio de Janeiro', 32669815),
    (3, 'Fortaleza', 2645857),
    (4, 'Salvador', 6767865),
    (5, 'Belo Horizonte', 425125),
    (6, 'Porto Alegre', 87987678),
    (7, 'Porto Velho', 678675),
    (8, 'Palmas', 36953),
    (9, 'Recife', 264586857),
    (10, 'Natal', 1654372),
    (11, 'Brasilia', 1000000),
    (12, 'Vitoria', 12),
    (13, 'Curitiba', 15);
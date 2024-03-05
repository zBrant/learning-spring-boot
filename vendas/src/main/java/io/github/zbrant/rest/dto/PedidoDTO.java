package io.github.zbrant.rest.dto;

import io.github.zbrant.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

  @NotNull(message = "informe o código do cliente")
  private Integer cliente;

  @NotNull(message = "campo total do pedido é obrigatório")
  private BigDecimal total;

  @NotEmptyList(message = "Pedido não pode ser realizado sem itens")
  private List<ItemPedidoDTO> items;

}

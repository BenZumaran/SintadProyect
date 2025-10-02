package com.sintad.gestion_clientes.model.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="users")
public class UserEntity {
	@Id
	@Column("id")
	private long id;
	@Column("username")
	private String username;
	@Column("role")
	private String role;
	@Column("pepper")
	private String pepper;
	@Column("password")
	private String password;
	
	
	
}

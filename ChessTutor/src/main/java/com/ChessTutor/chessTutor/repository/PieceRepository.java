package com.ChessTutor.chessTutor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.ChessTutor.chessTutor.model.Piece;
import com.ChessTutor.chessTutor.model.Square;

public interface PieceRepository extends Neo4jRepository<Piece, Long>{

	List<Piece> findAll();
	
	Optional<Piece> findById(Long id);
}

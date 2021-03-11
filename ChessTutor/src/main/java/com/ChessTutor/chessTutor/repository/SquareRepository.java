package com.ChessTutor.chessTutor.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import com.ChessTutor.chessTutor.model.Square;



@Repository
public interface SquareRepository extends Neo4jRepository<Square, Long> {

	//@Query("MATCH (au:Square)<-[a:IsOn]-(b:Piece) RETURN au,collect(a),collect(b)")
	List<Square> findAll();
	
	Square findByNameForGraph(String nameForGraph);
	
	Square save(Square square);
	
	@Query("MATCH (piece:Piece)-[:IsOn]->(square) WHERE(id(piece) = $pieceId) RETURN square")
	Square findSquareByPiece(Long pieceId);
	

	@Query("MATCH (p:Piece)-[:VISION_SQUARE]->(s:Square) WHERE id(p) = $pieceId RETURN s")
	List<Square> findVisionSquaresForPiece(Long pieceId);
	
	@Query("MATCH (s:Square {nameForGraph : $name})\r\n"
			+ "RETURN s")
	Square findSquareByGraphName(String name);
	
	@Query("MATCH (:Square)-[rel]->(s:Square) WHERE type(rel) = $direction RETURN s")
	List<Square> findByRelationshipTest(String direction);
}

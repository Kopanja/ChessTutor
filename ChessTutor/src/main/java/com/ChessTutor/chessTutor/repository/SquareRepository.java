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
	
	//---------Queries for PATHS---------------------------------------------------------------------------
	@Query("MATCH (s:Square)-[:DIAGONAL_LEFT_UP *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findDiagLeftUpPath(Long squareId);
	
	@Query("MATCH (s:Square)-[:DIAGONAL_RIGHT_UP *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findDiagRightUpPath(Long squareId);
	
	@Query("MATCH (s:Square)-[:DIAGONAL_LEFT_DOWN *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findDiagLeftDownPath(Long squareId);
	
	@Query("MATCH (s:Square)-[:DIAGONAL_RIGHT_DOWN *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findDiagRightDownPath(Long squareId);
	
	@Query("MATCH (s:Square)-[:UP *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findUpPath(Long squareId);
	
	@Query("MATCH (s:Square)-[:DOWN *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findDownPath(Long squareId);
	
	@Query("MATCH (s:Square)-[:RIGHT *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findRightPath(Long squareId);
	
	@Query("MATCH (s:Square)-[:LEFT *]->(s2:Square) WHERE id(s) = $squareId RETURN s2")
	List<Square> findLeftPath(Long squareId);
	
	
}

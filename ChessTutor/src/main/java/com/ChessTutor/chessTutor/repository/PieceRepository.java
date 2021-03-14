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
	
	@Query("MATCH (n:Piece)\r\n"
			+ "DETACH DELETE n")
	void removeAllPieces();
	
	@Query("MATCH (p:Piece{type : $pieceType}) RETURN p")
	List<Piece> findByTypeTest(String pieceType);
	
	//-----------------------------------------------------------
	
	@Query("MATCH (p:Piece{type : \"King\"})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH (s)-[rel]->(s2:Square)\r\n"
			+ "WHERE (NOT EXISTS((p)-[:VISION_SQUARE]->(s2))) AND (type(rel) = $relType)\r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s2)")
	void createVisionSquareForKing(String relType);
	
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:UP *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:UP]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionUP(String pieceType);
	
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:DOWN *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:DOWN]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionDOWN(String pieceType);
	
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:LEFT *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:LEFT]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionLEFT(String pieceType);
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:RIGHT *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:RIGHT]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionRIGHT(String pieceType);
	
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:DIAGONAL_RIGHT_UP *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:DIAGONAL_RIGHT_UP]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionDiagRightUP(String pieceType);
	
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:DIAGONAL_LEFT_UP *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:DIAGONAL_LEFT_UP]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionDiagLeftUP(String pieceType);
	
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:DIAGONAL_RIGHT_DOWN *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:DIAGONAL_RIGHT_DOWN]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionDiagRightDOWN(String pieceType);
	
	@Query("MATCH (p:Piece{type : $pieceType})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH ns = (s)-[:DIAGONAL_LEFT_DOWN *]->(s2:Square)\r\n"
			+ "UNWIND nodes(ns) as n\r\n"
			+ "MATCH (n)-[:DIAGONAL_LEFT_DOWN]->(s3)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3)) \r\n"
			+ "CREATE (p)-[r2:VISION_SQUARE]->(s3)")
	void createVisionDiagLeftDown(String pieceType);
	//-------------------------------------------------------------------------------------

	@Query("MATCH (p:Piece{type : \"Knight\"})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH (s)-[:UP]->(:Square)-[:UP]->(s2:Square)-[:LEFT|:RIGHT]->(s3:Square)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3))\r\n"
			+ "CREATE (p)-[:VISION_SQUARE]->(s3)")
	void createVisionKnightUP();
	
	@Query("MATCH (p:Piece{type : \"Knight\"})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH (s)-[:DOWN]->(:Square)-[:DOWN]->(s2:Square)-[:LEFT|:RIGHT]->(s3:Square)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3))\r\n"
			+ "CREATE (p)-[:VISION_SQUARE]->(s3)")
	void createVisionKnightDOWN();
	
	@Query("MATCH (p:Piece{type : \"Knight\"})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH (s)-[:LEFT]->(:Square)-[:LEFT]->(s2:Square)-[:UP|:DOWN]->(s3:Square)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3))\r\n"
			+ "CREATE (p)-[:VISION_SQUARE]->(s3)")
	void createVisionKnightLEFT();
	
	@Query("MATCH (p:Piece{type : \"Knight\"})-[r:IsOn]->(s:Square)\r\n"
			+ "MATCH (s)-[:RIGHT]->(:Square)-[:RIGHT]->(s2:Square)-[:UP|:DOWN]->(s3:Square)\r\n"
			+ "WHERE NOT EXISTS((p)-[:VISION_SQUARE]->(s3))\r\n"
			+ "CREATE (p)-[:VISION_SQUARE]->(s3)")
	void createVisionKnightRIGHT();
	
}

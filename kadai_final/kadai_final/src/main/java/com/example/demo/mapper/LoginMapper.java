package com.example.demo.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Login;


@Mapper
public interface LoginMapper {
//入力されたemail,passwordから該当データの検索
	@Select("SELECT * FROM loginuser WHERE name = #{name} AND password = #{password}")
	Login findByUser(String name, String password);
	
//検索MyBatis
	@Select({
	    "<script>",
	    "SELECT * FROM loginuser",
	    "WHERE 1=1",
	    "<if test='id != null'>AND id = #{id}</if>",
	    "<if test='name != null and name != \"\"'>AND name LIKE CONCAT('%', #{name}, '%')</if>",
	    "<if test='minAge != null'>AND age &gt;= #{minAge}</if>",
	    "<if test='maxAge != null'>AND age &lt;= #{maxAge}</if>",
	    "<if test='minStart != null'>AND start &gt;= #{minStart}</if>",
	    "<if test='maxStart != null'>AND start &lt;= #{maxStart}</if>",
	    "<if test='minEnd != null'>AND end &gt;= #{minEnd}</if>",
	    "<if test='maxEnd != null'>AND end &lt;= #{maxEnd}</if>",
	    "</script>"
	})
	List<Login> selectByConditions(
	    @Param("id") Long id,
	    @Param("name") String name,
	    @Param("minAge") Integer minAge,
	    @Param("maxAge") Integer maxAge,
	    @Param("minStart") LocalDate minStart,
	    @Param("maxStart") LocalDate maxStart,
	    @Param("minEnd") LocalDate minEnd,
	    @Param("maxEnd") LocalDate maxEnd
	);
	
	//削除機能MyBatis
	@Delete("DELETE FROM loginuser WHERE id = #{id}")
	void delete(Long id);

	@Delete({
	    "<script>",
	    "DELETE FROM loginuser WHERE id IN",
	    "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
	    "#{id}",
	    "</foreach>",
	    "</script>"
	})
	void deleteByIds(@Param("ids") List<Long> ids);

	// 複数取得（削除確認用）
	@Select({
	    "<script>",
	    "SELECT * FROM loginuser WHERE id IN",
	    "<foreach item='id' collection='ids' open='(' separator=',' close=')'>",
	    "#{id}",
	    "</foreach>",
	    "</script>"
	})
	List<Login> findByIds(@Param("ids") List<Long> ids);
	
//情報更新	
	@Update("UPDATE loginuser SET name = #{name}, email = #{email}, age = #{age}, password = #{password}, end = NOW() WHERE id = #{id}")
	void update(Login login);
	



	    @Insert("INSERT INTO loginuser(name, age, email, password, start, end) VALUES(#{name}, #{age}, #{email}, #{password}, NOW(), NOW())")
	    @Options(useGeneratedKeys = true, keyProperty = "id")
	    void insert(Login login);

    

}
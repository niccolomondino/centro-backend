package com.tecnositaf.centrobackend.mapper;



import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.tecnositaf.centrobackend.model.User;

@Mapper
public interface UserMapper {

	
	
	@Select("SELECT * FROM Users WHERE idUser = #{idUser}")
	User getUser(User user);

	@Select("SELECT * FROM Users WHERE idUser = #{idUser}")
    User getUserById(Integer idUser);
    
    @Select("SELECT * FROM Users")
    List<User> findAll();
    
    @Insert("INSERT INTO Users(birthDate, name, password) " +
            " VALUES (#{birthDate}, #{name}, #{password})")
    @Options(useGeneratedKeys=true,keyProperty="idUser",keyColumn="idUser")
    int insert(User user);
    
    @Select("SELECT * FROM Users WHERE birthDate = #{birthDate}")
	List<User> findByBirthDate(LocalDate birthDate);

    @Delete("DELETE FROM Users")
    int deleteAll();

    @Delete("DELETE FROM Users WHERE idUser=#{idUser}")
	int deleteById(Integer idUser);
    
}

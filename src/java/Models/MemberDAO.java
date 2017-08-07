/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;


public interface MemberDAO {

public void createMember(Members mem);    

Members getMember(int id);

List<Members> listMembers();

void changeStatus(Members mem);

void update(Members mem);

}

package bl.fpdual.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import bl.fpdual.user.model.User;
import bl.fpdual.user.model.UserException;
import bl.fpdual.user.repository.UserRepository;
import bl.fpdual.user.service.impl.UserServiceImpl;

public class UserServiceImplTest {

	private UserServiceImpl userServiceImpl;
	private final UserRepository userRepository = mock(UserRepository.class);
	private static final String NICK_NAME = "myNickName";
	private final User modifiedUser = mock(User.class);

	@BeforeEach
	public void intialize() {
		userServiceImpl = new UserServiceImpl(userRepository);
	}

	@Test
	public void shouldExceptionIfModifyNotExistingUser() {
		when(userRepository.userExists(NICK_NAME)).thenReturn(false);
		assertThrows(UserException.class, () -> userServiceImpl.modifyExistingUser(NICK_NAME, modifiedUser));
	}

	@Test
	public void shouldExceptionIfModifyUserWithDifferentExistingNickName() {
		when(userRepository.userExists(NICK_NAME)).thenReturn(true);
		when(modifiedUser.getNickName()).thenReturn("Julio");
		when(userRepository.userExists("Julio")).thenReturn(true);
		assertThrows(UserException.class, () -> userServiceImpl.modifyExistingUser(NICK_NAME, modifiedUser));
	}

	@Test
	public void shouldModifyUser() {
		when(userRepository.userExists(NICK_NAME)).thenReturn(true);
		when(modifiedUser.getNickName()).thenReturn(NICK_NAME);

		userServiceImpl.modifyExistingUser(NICK_NAME, modifiedUser);

		verify(userRepository, times(1)).modifyExistingUser(NICK_NAME, modifiedUser);
	}

	@Test
	public void shouldExceptionIfAddingExistingUser() {
		when(modifiedUser.getNickName()).thenReturn(NICK_NAME);
		when(userRepository.userExists(NICK_NAME)).thenReturn(true);
		assertThrows(UserException.class, () -> userServiceImpl.addNewUser(modifiedUser));
	}

	@Test
	public void shouldAddUser() {
		when(modifiedUser.getNickName()).thenReturn(NICK_NAME);
		when(userRepository.userExists(NICK_NAME)).thenReturn(false);

		userServiceImpl.addNewUser(modifiedUser);

		verify(userRepository, times(1)).addNewUser(modifiedUser);
	}

	@Test
	public void shouldGetUserListByCountry() {
		List<User> usersReturnedByRepository = new ArrayList<>();
		when(userRepository.getUserListByCountry("es")).thenReturn(usersReturnedByRepository);
		final List<User> userList = userServiceImpl.getUserListByCountry("es");
		assertSame(usersReturnedByRepository, userList);
	}

	@Test
	public void shouldRemoveUser() {
		userServiceImpl.removeUser(NICK_NAME);

		verify(userRepository, times(1)).removeUser(NICK_NAME);
	}

}

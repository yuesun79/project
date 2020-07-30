package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.User;
import dao.UserDao;
import impls.UserDaoJdbcImpl;

/**
 * Servlet implementation class RegsiterServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	 // The following constants may be changed without breaking existing hashes.
	public static final int SALT_BYTE_SIZE = 24;
	public static final int HASH_BYTE_SIZE = 24;
	public static final int PBKDF2_ITERATIONS = 1000;
	
	public static final int ITERATION_INDEX = 0;
	public static final int SALT_INDEX = 1;
	public static final int PBKDF2_INDEX = 2;
	  
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao userDao = new UserDaoJdbcImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		int hasUser = (int) userDao.getCountWithName(username);
		if (hasUser == 1) {
			String message = "用户名已存在";
			request.setAttribute("message", message);
			request.setAttribute("username", username);
			request.setAttribute("email", email);
		}else {
			try {
				
				System.out.println(username);
				System.out.println(password);
				
				password = createHash(password);
				
				System.out.println(password);
				
				User user = new User(email, username, password);
				userDao.addUser(user);
				User matchUser = userDao.getUser(user);
				if (matchUser != null) {
					request.getSession().setAttribute("user",matchUser);
					response.sendRedirect(request.getContextPath()+"/index.jsp");
				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	/**
	 * Returns a salted PBKDF2 hash of the password.
	 *
	 * @param   password    the password to hash
	 * @return              a salted PBKDF2 hash of the password
	 */
	public static String createHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		return createHash(password.toCharArray());
	}
	
	/**
	 * Returns a salted PBKDF2 hash of the password.
	 *
	 * @param   password    the password to hash
	 * @return              a salted PBKDF2 hash of the password
	 */
	public static String createHash(char[] password)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		// Generate a random salt
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		
		// Hash the password
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		// format iterations:salt:hash
		return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
	}
	
	/**
	 * Validates a password using a hash.
	 *
	 * @param   password        the password to check
	 * @param   correctHash     the hash of the valid password
	 * @return                  true if the password is correct, false if not
	 */
	public static boolean validatePassword(String password, String correctHash)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		return validatePassword(password.toCharArray(), correctHash);
	}
	
	/**
	 * Validates a password using a hash.
	 *
	 * @param   password        the password to check
	 * @param   correctHash     the hash of the valid password
		104      * @return                  true if the password is correct, false if not
		105      */
	public static boolean validatePassword(char[] password, String correctHash)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		// Decode the hash into its parameters
		String[] params = correctHash.split(":");
		int iterations = Integer.parseInt(params[ITERATION_INDEX]);
		byte[] salt = fromHex(params[SALT_INDEX]);
		byte[] hash = fromHex(params[PBKDF2_INDEX]);
		// Compute the hash of the provided password, using the same salt, 
		// iteration count, and hash length
		byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
		// Compare the hashes in constant time. The password is correct if
		// both hashes match.
		return slowEquals(hash, testHash);
	}
	
	/**
	 * Compares two byte arrays in length-constant time. This comparison method
	 * is used so that password hashes cannot be extracted from an on-line 
	 * system using a timing attack and then attacked off-line.
	 * 
	 * @param   a       the first byte array
	 * @param   b       the second byte array 
	 * @return          true if both byte arrays are the same, false if not
	 */
	private static boolean slowEquals(byte[] a, byte[] b)
	{
		int diff = a.length ^ b.length;
		for(int i = 0; i < a.length && i < b.length; i++)
			diff |= a[i] ^ b[i];
		return diff == 0;
	}
	
	/**
	 *  Computes the PBKDF2 hash of a password.
	 *
	 * @param   password    the password to hash.
	 * @param   salt        the salt
	 * @param   iterations  the iteration count (slowness factor)
	 * @param   bytes       the length of the hash to compute in bytes
	 * @return              the PBDKF2 hash of the password
	 */
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
		return skf.generateSecret(spec).getEncoded();
	}
	
	/**
	 * Converts a string of hexadecimal characters into a byte array.
	 *
	 * @param   hex         the hex string
	 * @return              the hex string decoded into a byte array
	 */
	private static byte[] fromHex(String hex)
	{
		byte[] binary = new byte[hex.length() / 2];
		for(int i = 0; i < binary.length; i++)
		{
			binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
		}
		return binary;
	}
	
	/**
	 * Converts a byte array into a hexadecimal string.
	 *
	 * @param   array       the byte array to convert
	 * @return              a length*2 character string encoding the byte array
	 */
	private static String toHex(byte[] array)
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if(paddingLength > 0) 
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}
}

import java.util.*;
import java.io.*;

public class JosephusSim
{
	private PersonNode circle;     // a PersonNode pointer that tracks first node
	private int size;              // the number of people in the circle
	private int eliminationCount;  // the number to count to for elimination
	private PersonNode track;      // a PersonNode pointer to help with elimination

	public JosephusSim(String fileName)
	{
		size = 0;
		try
		{
			// load names from the file in order, generating a singly linked list of PersonNodes
			Scanner file = new Scanner(new File(fileName));
			circle = new PersonNode(file.next());
			size++;
			track = circle;
			while (file.hasNext())
			{
				track.next = new PersonNode(file.next());
				track = track.next;
				size++;
			}

			// make the ring circular by attaching last node's next to front
			track.next = circle;

			// generate, print, and save the random elimination count
			Random rand = new Random();
			eliminationCount = rand.nextInt(size / 2) + 1;
			System.out.println("=== Elimination count is " + eliminationCount + " ===");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Something went wrong with " + fileName);
		}
	}

	public void eliminate()
	{
		// count to the elimination count
		for (int i = 0; i < eliminationCount - 1; i++)
		{
			track = track.next;
		}

		// print who will be eliminated
		System.out.println("\n" + track.next.name + " eliminated!");

		// eliminate the person and update "front" of the circle and size
		track.next = track.next.next;
		circle = track.next;
		size--;
	}

	public boolean isOver()
	{
		// check if there's only one person left in the circle
		return size == 1;
	}

	public String toString()
	{
		// if there's only one person left, print them as the last survivor
		if (isOver())
		{
			return circle.name + " is the last survivor!";
		}

		// print the remaining survivors (watch out for infinite loop since list is circular)
		String result = "";
		PersonNode current = circle;
		int count = 1;

		do
		{
			if (count > 1)
				result += ", ";
			result += count + "-" + current.name;
			current = current.next;
			count++;
		} while (current != circle);

		return "Remaining survivors: " + result;
	}
}

/*
# PROGRAM OUTPUT

=== Elimination count is 2 ===
Remaining survivors: 1-Marcelle, 2-Hashir, 3-Boubacar, 4-edgar, 5-Nelson, 6-Sarinya, 7-Dario, 8-Joaquin, 9-Aurel, 10-Crystal, 11-Michael, 12-Jesse, 13-Joshua, 14-Thomas, 15-Angelina, 16-Visal, 17-Zoheb, 18-Owen, 19-Marc, 20-Grace, 21-Milady, 22-Lily

Continue elimination? 

Hashir eliminated!
Remaining survivors: 1-Boubacar, 2-edgar, 3-Nelson, 4-Sarinya, 5-Dario, 6-Joaquin, 7-Aurel, 8-Crystal, 9-Michael, 10-Jesse, 11-Joshua, 12-Thomas, 13-Angelina, 14-Visal, 15-Zoheb, 16-Owen, 17-Marc, 18-Grace, 19-Milady, 20-Lily, 21-Marcelle

Continue elimination? 

edgar eliminated!
Remaining survivors: 1-Nelson, 2-Sarinya, 3-Dario, 4-Joaquin, 5-Aurel, 6-Crystal, 7-Michael, 8-Jesse, 9-Joshua, 10-Thomas, 11-Angelina, 12-Visal, 13-Zoheb, 14-Owen, 15-Marc, 16-Grace, 17-Milady, 18-Lily, 19-Marcelle, 20-Boubacar

Continue elimination? 

Sarinya eliminated!
Remaining survivors: 1-Dario, 2-Joaquin, 3-Aurel, 4-Crystal, 5-Michael, 6-Jesse, 7-Joshua, 8-Thomas, 9-Angelina, 10-Visal, 11-Zoheb, 12-Owen, 13-Marc, 14-Grace, 15-Milady, 16-Lily, 17-Marcelle, 18-Boubacar, 19-Nelson

Continue elimination? 

Joaquin eliminated!
Remaining survivors: 1-Aurel, 2-Crystal, 3-Michael, 4-Jesse, 5-Joshua, 6-Thomas, 7-Angelina, 8-Visal, 9-Zoheb, 10-Owen, 11-Marc, 12-Grace, 13-Milady, 14-Lily, 15-Marcelle, 16-Boubacar, 17-Nelson, 18-Dario

Continue elimination? 

Crystal eliminated!
Remaining survivors: 1-Michael, 2-Jesse, 3-Joshua, 4-Thomas, 5-Angelina, 6-Visal, 7-Zoheb, 8-Owen, 9-Marc, 10-Grace, 11-Milady, 12-Lily, 13-Marcelle, 14-Boubacar, 15-Nelson, 16-Dario, 17-Aurel

Continue elimination? 

Jesse eliminated!
Remaining survivors: 1-Joshua, 2-Thomas, 3-Angelina, 4-Visal, 5-Zoheb, 6-Owen, 7-Marc, 8-Grace, 9-Milady, 10-Lily, 11-Marcelle, 12-Boubacar, 13-Nelson, 14-Dario, 15-Aurel, 16-Michael

Continue elimination? 

Thomas eliminated!
Remaining survivors: 1-Angelina, 2-Visal, 3-Zoheb, 4-Owen, 5-Marc, 6-Grace, 7-Milady, 8-Lily, 9-Marcelle, 10-Boubacar, 11-Nelson, 12-Dario, 13-Aurel, 14-Michael, 15-Joshua

Continue elimination? 

Visal eliminated!
Remaining survivors: 1-Zoheb, 2-Owen, 3-Marc, 4-Grace, 5-Milady, 6-Lily, 7-Marcelle, 8-Boubacar, 9-Nelson, 10-Dario, 11-Aurel, 12-Michael, 13-Joshua, 14-Angelina

Continue elimination? 

Owen eliminated!
Remaining survivors: 1-Marc, 2-Grace, 3-Milady, 4-Lily, 5-Marcelle, 6-Boubacar, 7-Nelson, 8-Dario, 9-Aurel, 10-Michael, 11-Joshua, 12-Angelina, 13-Zoheb

Continue elimination? 

Grace eliminated!
Remaining survivors: 1-Milady, 2-Lily, 3-Marcelle, 4-Boubacar, 5-Nelson, 6-Dario, 7-Aurel, 8-Michael, 9-Joshua, 10-Angelina, 11-Zoheb, 12-Marc

Continue elimination? 

Lily eliminated!
Remaining survivors: 1-Marcelle, 2-Boubacar, 3-Nelson, 4-Dario, 5-Aurel, 6-Michael, 7-Joshua, 8-Angelina, 9-Zoheb, 10-Marc, 11-Milady

Continue elimination? 

Boubacar eliminated!
Remaining survivors: 1-Nelson, 2-Dario, 3-Aurel, 4-Michael, 5-Joshua, 6-Angelina, 7-Zoheb, 8-Marc, 9-Milady, 10-Marcelle

Continue elimination? 

Dario eliminated!
Remaining survivors: 1-Aurel, 2-Michael, 3-Joshua, 4-Angelina, 5-Zoheb, 6-Marc, 7-Milady, 8-Marcelle, 9-Nelson

Continue elimination? 

Michael eliminated!
Remaining survivors: 1-Joshua, 2-Angelina, 3-Zoheb, 4-Marc, 5-Milady, 6-Marcelle, 7-Nelson, 8-Aurel

Continue elimination? 

Angelina eliminated!
Remaining survivors: 1-Zoheb, 2-Marc, 3-Milady, 4-Marcelle, 5-Nelson, 6-Aurel, 7-Joshua

Continue elimination? 

Marc eliminated!
Remaining survivors: 1-Milady, 2-Marcelle, 3-Nelson, 4-Aurel, 5-Joshua, 6-Zoheb

Continue elimination? 

Marcelle eliminated!
Remaining survivors: 1-Nelson, 2-Aurel, 3-Joshua, 4-Zoheb, 5-Milady

Continue elimination? 

Aurel eliminated!
Remaining survivors: 1-Joshua, 2-Zoheb, 3-Milady, 4-Nelson

Continue elimination? 

Zoheb eliminated!
Remaining survivors: 1-Milady, 2-Nelson, 3-Joshua

Continue elimination? 

Nelson eliminated!
Remaining survivors: 1-Joshua, 2-Milady

Continue elimination? 

Milady eliminated!
Joshua is the last survivor!
*/